package com.forum.Dao;

import com.forum.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDao extends JpaRepository<User,Integer> {

//    int selectEmailCount(String email);
//
//    void insertUser(User user);
//
//    User selectUserByUid(int uid);
//
//    Integer selectUidByEmailAndPassword(User user);
//
//      User selectUsernameByUid(int uid);
//
//    String selectPasswordByUid(int uid);

//    List<User> listUserByTime();
//
//    List<User> listUserByHot();

}
