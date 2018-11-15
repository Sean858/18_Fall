package com.forum.Dao;

import com.forum.Entity.Comment;
import com.forum.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    int selectEmailCount(String email);

    void insertUser(User user);

    User selectUserByUid(Integer uid);

    int selectUidByEmailAndPassword(User user);

    User selectEditInfo(int uid);

    User updateUser(User user);



    void updatePostCount(Integer uid);

    void updateScanCount(int uid);

    List<User> listUserByTime();

    List<User> listUserByLike();

    User selectUsernameByUid(int uid);

    String selectPasswordByUid(int uid);



}
