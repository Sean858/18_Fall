package com.forum.Service;

import com.forum.Dao.UserDao;
import com.forum.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JedisPool jedisPool;

    public User getProfile(int targetUid, int uid) {
        //If scan other's profile, add scanCount
        if (targetUid != uid) {
            userDao.updateScanCount(uid);
        }
        //Get User
        User user = userDao.selectUserByUid(uid);

        //Set like, follow, and follower
        Jedis jedis = jedisPool.getResource();
        user.setFollowCount((int) (long) jedis.scard("Follow: " + uid));
        user.setFollowerCount((int) (long) jedis.scard("Followers: " + uid));
        String likeCount = jedis.hget("like", uid + "");

        user.setLikeCount(Integer.valueOf(likeCount));

        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
        return user;
    }

    public User getEditInfo(int uid) {
        return userDao.selectEditInfo(uid);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public List<User> listUserByTime() { return userDao.listUserByTime();}

    public List<User> listUserByLike() { return userDao.listUserByLike();}

    public void unfollow(int targetUid, int uid) {
        Jedis jedis = jedisPool.getResource();
        Transaction tx = jedis.multi();
        tx.srem("Follow" + targetUid, String.valueOf(uid));
        tx.srem("Followers: " + uid, String.valueOf(targetUid));
        tx.exec();

        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }

    public void follow(int targetUid, int uid) {
        Jedis jedis = jedisPool.getResource();
        Transaction tx = jedis.multi();
        tx.sadd("Follow" + targetUid, String.valueOf(uid));
        tx.sadd("Followers: " + uid, String.valueOf(targetUid));
        tx.exec();
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }

    public boolean getFollowStatus(int targetUid, int uid) {
        Jedis jedis = jedisPool.getResource();
        boolean following = jedis.sismember("Follow" + targetUid, String.valueOf(uid));
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
        return following;
    }

}

