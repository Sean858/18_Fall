package com.forum.Service;

import com.forum.Dao.CommentDao;
import com.forum.Dao.PostDao;
import com.forum.Dao.UserDao;
import com.forum.Entity.Comment;
import com.forum.Entity.Post;
import com.forum.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;


    //Comment
    public void comment(int sessionUid, int pid, String content) {
        //construct comment user
        User user = new User(sessionUid);
        Post post = new Post(pid);
        Comment comment =new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);
        //insert a comment
        commentDao.insertComment(comment);
        //update the comment number of a thread
        postDao.updateReplyCount(pid);
        //update newest comment time
        postDao.updateReplyTime(pid);

    }

    //list comment according to thread
    public List<Comment> listComment(int pid) {

        List<Comment> commentList = commentDao.listComment(pid);
        for(Comment comment : commentList){
            //list the comment of comment
            List<Comment> comments = commentDao.listComment(comment.getCid());
            comment.setCommentList(comments);
        }
        return commentList;
    }
}

