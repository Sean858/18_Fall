package com.forum.Controller;

import com.forum.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/comment.do")
    public String reply(int pid, String content, HttpSession session){
        int sessionUid = (int) session.getAttribute("uid");
        commentService.comment(sessionUid,pid,content);
        return "redirect:toPost.do?pid="+pid;
    }


}
