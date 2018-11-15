package com.forum.Service;

import com.forum.Dao.PostDao;
import com.forum.Dao.TopicDao;
import com.forum.Entity.Topic;
import com.forum.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    public Post save(Post post){

        if((post.getPid() != null) && (postDao.existsById(post.getPid()))){
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return postDao.save(post);
    }

    public Post update(Post post){

        if(post.getPid() != null && (postDao.existsById(post.getPid()))) {
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return postDao.save(post);
    }

    public List<Post> findAll() {
        return postDao.findAll();
    }

    public Post findOne(Integer id) {
        return postDao.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        postDao.deleteById(id);
    }


}
