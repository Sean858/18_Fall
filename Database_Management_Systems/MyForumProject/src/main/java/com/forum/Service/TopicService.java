package com.forum.Service;

import com.forum.Dao.TopicDao;
import com.forum.Entity.Topic;
import com.forum.Entity.User;

import javax.persistence.EntityExistsException;
import java.util.List;

public class TopicService {

    private TopicDao topicDao;

    public Topic save(Topic topic){

        if((topic.getTid() != null) && (topicDao.existsById(topic.getTid()))){
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return topicDao.save(topic);
    }

    public Topic update(Topic topic){

        if(topic.getTid() != null && (topicDao.existsById(topic.getTid()))) {
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return topicDao.save(topic);
    }

    public List<Topic> findAll() {
        return topicDao.findAll();
    }

    public Topic findOne(Integer id) {
        return topicDao.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        topicDao.deleteById(id);
    }
}
