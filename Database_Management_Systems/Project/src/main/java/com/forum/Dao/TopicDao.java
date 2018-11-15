package com.forum.Dao;

import com.forum.Entity.Comment;
import com.forum.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicDao extends JpaRepository<Topic,Integer> {

    List<Topic> listTopic();

}
