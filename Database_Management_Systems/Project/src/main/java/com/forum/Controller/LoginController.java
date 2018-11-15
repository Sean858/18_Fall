package com.forum.Controller;

import com.forum.Entity.User;
import com.forum.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * Go Dashboard
     * @return
     */
    @RequestMapping("/toLogin.do")
    public String toLogin(){
        return "login";
    }

    /**
     * Register
     * @param user
     * @param repassword
     * @param model
     * @return
     */
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public String register(User user, String repassword, Model model){
        String result = loginService.register(user,repassword);
        model.addAttribute("register","yes");
        model.addAttribute("email",user.getEmail());
        model.addAttribute("error",result);
        return "login";
    }

    /**
     * Login
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session){
        Map<String,Object> map = loginService.login(user);
        if(map.get("status").equals("yes")){
            session.setAttribute("uid",map.get("uid"));
            session.setAttribute("headUrl",map.get("headUrl"));
            return "redirect:toMyProfile.do";
        }else {
            model.addAttribute("email",user.getEmail());
            model.addAttribute("error",map.get("error"));
            return "login";
        }
    }

    /**
     * Logout
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("uid");
        return "redirect:toIndex.do";
    }
}


