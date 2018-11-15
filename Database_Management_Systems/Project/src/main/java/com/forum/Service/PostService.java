package com.forum.Service;

import com.forum.Dao.PostDao;
import com.forum.Dao.UserDao;
import com.forum.Entity.Page;
import com.forum.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Pool<Jedis> jedisPool;

//    @Autowired
//    private TaskExecutor taskExecutor;

    //get thread list according to userid
    public List<Post> getPostList(int uid) {
        return postDao.listPostByUid(uid);
    }

    public static String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        return dateFormat.format(date);
    }

    public int publishPost(Post post) {

        //build thread
        post.setPublishTime(this.formatDate(new Date()));
        post.setReplyTime(this.formatDate(new Date()));
        post.setScanCount(0);
        post.setLikeCount(0);
        post.setReplyCount(0);

        //add a post
        postDao.insertPost(post);
        System.out.println(post.getPid());
        //update user post number
        userDao.updatePostCount(post.getUser().getUid());

        return post.getPid();
    }

    //list thread according to time line
    public Page<Post> listPostByTime(int curPage) {
        //record each page
        int limit = 10;
        int offset = (curPage-1) * limit;
        //total record
        int allCount = postDao.selectPostCount();
        int allPage = 0;
        if (allCount <= limit) {
            allPage = 1;
        } else if (allCount / limit == 0) {
            allPage = allCount / limit;
        } else {
            allPage = allCount / limit + 1;
        }
        //divide page
        List<Post> postList = postDao.listPostByTime(offset,limit);
        Jedis jedis = jedisPool.getResource();
        for(Post post : postList){
            post.setLikeCount((int)(long)jedis.scard(post.getPid()+":like"));
        }

        //construct page
        Page<Post> pageBean = new Page<>(allPage,curPage);
        pageBean.setList(postList);

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return pageBean;
    }


    public Post getPostByPid(int pid) {
        //reflash likecount
        postDao.updateScanCount(pid);
        Post post =postDao.getPostByPid(pid);
        //setLikeCount
        Jedis jedis = jedisPool.getResource();
        long likeCount = jedis.scard(pid+":like");
        post.setLikeCount((int)likeCount);

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return post;
    }

    //Like
    public String clickLike(int pid, int sessionUid) {
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(pid+":like", String.valueOf(sessionUid));
        //increase likecount
        jedis.hincrBy("vote",sessionUid+"",1);

        String result = String.valueOf(jedis.scard(pid+":like"));
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    //Whether the user has had a like
    public boolean getLikeStatus(int pid, int sessionUid) {
        Jedis jedis = jedisPool.getResource();
        boolean result = jedis.sismember(pid+":like", String.valueOf(sessionUid));

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return result;
    }
}

