package com.forum.Dao;

import com.forum.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Integer> {

    void insertComment(Comment comment);

    List<Comment> listComment(int pid);

    String getContentByCid(int cid);

}
