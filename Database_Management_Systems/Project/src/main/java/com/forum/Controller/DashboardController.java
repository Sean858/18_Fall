package com.forum.Controller;

import com.forum.Entity.Page;
import com.forum.Entity.Post;
import com.forum.Entity.User;
import com.forum.Service.PostService;
import com.forum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    /**
     * Go Dashboard
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/toDashboard.do")
    public String toDashboard(Model model, HttpServletRequest request){

        Page<Post> page = postService.listPostByTime(1);
        List<User> userList = userService.listUserByTime();
        List<User> likeUserList = userService.listUserByLike();

        model.addAttribute("page",page);
        model.addAttribute("userList",userList);
        model.addAttribute("hotUserList",likeUserList);
        return "index";
    }


}
