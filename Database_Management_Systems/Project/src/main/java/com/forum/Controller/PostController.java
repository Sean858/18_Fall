package com.forum.Controller;

import com.forum.Entity.*;
import com.forum.Service.CommentService;
import com.forum.Service.PostService;
import com.forum.Service.TopicService;
import com.forum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;


    //Go post page
    @RequestMapping("/toPublish.do")
    public String toPublish(Model model){
        List<Topic> topicList = topicService.listTopic();
        model.addAttribute("topicList",topicList);
        return "publish";
    }

    //Post
    @RequestMapping("/publishPost.do")
    public String publishPost(Post post) {
        int id = postService.publishPost(post);
        return "redirect:toPost.do?pid="+id;
    }


    //List threads
    @RequestMapping("/listPostByTime.do")
    public String listPostByTime(int curPage,Model model){
        Page<Post> page = postService.listPostByTime(curPage);
        List<User> userList = userService.listUserByTime();
        List<User> likeUserList = userService.listUserByLike();
        model.addAttribute("page",page);
        model.addAttribute("userList",userList);
        model.addAttribute("likeUserList",likeUserList);
        return "index";
    }

    //Go list page
    @RequestMapping("/toPost.do")
    public String toPost(int pid,Model model,HttpSession session){
        Integer sessionUid = (Integer) session.getAttribute("uid");

        Post post = postService.getPostByPid(pid);
        List<Comment> commentList = commentService.listComment(pid);

        //Whether like

        boolean liked = false;
        if(sessionUid!=null){
            liked = postService.getLikeStatus(pid,sessionUid);
        }

        model.addAttribute("post",post);
        model.addAttribute("commentList",commentList);
        model.addAttribute("liked",liked);
        return "post";
    }

    @RequestMapping(value = "/ajaxClickLike.do",produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String ajaxClickLike(int pid, HttpSession session){
        int sessionUid = (int) session.getAttribute("uid");
        return postService.clickLike(pid,sessionUid);
    }
}
