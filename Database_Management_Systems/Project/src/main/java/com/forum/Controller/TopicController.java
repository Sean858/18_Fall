package com.forum.Controller;

import com.forum.Entity.Topic;
import com.forum.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * List all topics
     * @param model
     * @return
     */
    @RequestMapping("/listTopic.do")
    public String listTopic(Model model){
        List<Topic> topicList = topicService.listTopic();
        model.addAttribute("topicList",topicList);
        return "topic";
    }

}
