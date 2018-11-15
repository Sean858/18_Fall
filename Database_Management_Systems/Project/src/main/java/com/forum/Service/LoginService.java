package com.forum.Service;

import com.forum.Dao.UserDao;
import com.forum.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginService {


    @Autowired
    private UserDao userDao;

    //Register
    public String register(User user, String repassword) {

        //Verify Email
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
        Matcher m = p.matcher(user.getEmail());
        if(!m.matches()){
            return "Wrong";
        }

        //Verify password
        p = Pattern.compile("^\\w{6,20}$");
        m = p.matcher(user.getPassword());
        if(!m.matches()){
            return "Please between 6 ~ 20";
        }

        //Recheck password
        if(!user.getPassword().equals(repassword)){
            return "Different";
        }

        //Verify whether email is registered
        int emailCount = userDao.selectEmailCount(user.getEmail());
        if(emailCount>0){
            return "Already registed";
        }

        //Insert record to db
        userDao.insertUser(user);

        return "ok";
    }



    //Login
    public Map<String,Object> login(User user) {

        Map<String,Object> map = new HashMap<>();
        Integer uid = userDao.selectUidByEmailAndPassword(user);
        if(uid==null){
            map.put("status","no");
            map.put("error","userid or password error");
            return map;
        }

        map.put("status","yes");
        map.put("uid",uid);
        return map;
    }

}
