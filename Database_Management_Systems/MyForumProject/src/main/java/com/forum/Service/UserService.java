package com.forum.Service;

import com.forum.Dao.UserDao;
import com.forum.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

//    public List<User> listUserByTime() {
//        return userDao.listUserByTime();
//    }
//
//    public List<User> listUserByHot() {
//        return userDao.listUserByHot();
//    }

    public User save(User user){

        if((user.getUid() != null) && (userDao.existsById(user.getUid()))){
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return userDao.save(user);
    }

    public User update(User user){

        if(user.getUid() != null && (userDao.existsById(user.getUid()))) {
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findOne(Integer id) {
        return userDao.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        userDao.deleteById(id);
    }

//    public User selectUsernameByUid(int id) {return userDao.findById(id).orElse(null).getUname();};


}
