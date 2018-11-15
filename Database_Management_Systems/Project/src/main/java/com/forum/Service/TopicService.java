package com.forum.Service;

import com.forum.Dao.TopicDao;
import com.forum.Entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicDao topicDao;

    public List<Topic> listTopic() {
        return topicDao.listTopic();
    }

}
