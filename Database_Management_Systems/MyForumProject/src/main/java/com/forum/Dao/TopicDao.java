package com.forum.Dao;

import com.forum.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicDao extends JpaRepository<Topic, Integer> {
}
