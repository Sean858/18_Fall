package com.forum.Controller;

import com.forum.Entity.Topic;
import com.forum.Entity.User;
import com.forum.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityExistsException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopicController {
    @Autowired
    private TopicService topicService;

    public TopicController(TopicService topicService){
        this.topicService = topicService;
    }

    @RequestMapping(value="topic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Topic> getAlltopics(){
        return topicService.findAll();
    }

    @RequestMapping(value="topic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) throws URISyntaxException {
        try{
            Topic result = topicService.save(topic);
            return ResponseEntity.created(new URI("/api/topic" + result.getTid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<Topic>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="topic", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> updatetopic(@RequestBody Topic topic) throws URISyntaxException{
        if(topic.getTid() == null){
            return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
        }

        try{
            Topic result = topicService.update(topic);
            return ResponseEntity.created(new URI("/api/topic" + result.getTid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/topic/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletetopic(@PathVariable Integer id){
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="topic/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectTopicTitleByPid(@PathVariable Integer id) {
        return topicService.findOne(id).getTopic();

    }
}
