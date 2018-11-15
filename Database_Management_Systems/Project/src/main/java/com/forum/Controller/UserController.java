package com.forum.Controller;

import com.forum.Entity.User;
import com.forum.Service.PostService;
import com.forum.Service.UserService;
import com.forum.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    /**
     * See own profile
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toMyProfile.do")
    public String toMyProfile(HttpSession session, Model model) {
        int sessionUid = (int) session.getAttribute("uid");
        User user = userService.getProfile(sessionUid, sessionUid);
        List<Post> postList = postService.getPostList(sessionUid);
        model.addAttribute("user", user);
        model.addAttribute("postList", postList);
        return "myProfile";
    }


    /**
     * Check other's profile
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping("/toProfile.do")
    public String toProfile(int uid, Model model, HttpSession session) {
        //If is own
        int sessionUid = (int) session.getAttribute("uid");
        if (sessionUid == uid) {
            return "redirect:toMyProfile.do";
        }
        //Judge whether follow
        boolean following = userService.getFollowStatus(sessionUid, uid);
        //Get sessionUid information
        User user = userService.getProfile(sessionUid, uid);
        //Get postList
        List<Post> postList = postService.getPostList(uid);

        model.addAttribute("following", following);
        model.addAttribute("user", user);
        model.addAttribute("postList", postList);
        return "profile";
    }


    /**
     * Go to edit profile
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toEditProfile.do")
    public String toEditProfile(HttpSession session, Model model) {
        int uid = (int) session.getAttribute("uid");
        User user = userService.getEditInfo(uid);
        model.addAttribute("user", user);
        return "editProfile";
    }

    /**
     * Edit profile
     * @param user
     * @return
     */
    @RequestMapping("/editProfile.do")
    public String editProfile(User user) {
        System.out.println(user);
        userService.updateUser(user);
        return "redirect:toMyProfile.do";
    }


    @RequestMapping("/follow.do")
    public String follow(int uid, HttpSession session) {
        int sessionUid = (int) session.getAttribute("uid");
        userService.follow(sessionUid, uid);
        return "forward:toProfile.do";
    }

    @RequestMapping("/unfollow.do")
    public String unfollow(int uid, HttpSession session) {
        int sessionUid = (int) session.getAttribute("uid");
        userService.unfollow(sessionUid, uid);
        return "forward:toProfile.do";
    }

}
