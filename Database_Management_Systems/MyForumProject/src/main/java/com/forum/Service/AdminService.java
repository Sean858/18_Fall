package com.forum.Service;

import com.forum.Dao.AdminDao;
import com.forum.Entity.Admin;
import com.forum.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

//    public List<Admin> listAdminByTime() {
//        return AdminDao.listAdminByTime();
//    }
//
//    public List<Admin> listAdminByHot() {
//        return adminDao.listAdminByHot();
//    }

    public Admin save(Admin admin){

        if((admin.getAid() != null) && (adminDao.existsById(admin.getAid()))){
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return adminDao.save(admin);
    }

    public Admin update(Admin admin){

        if(admin.getAid() != null && (adminDao.existsById(admin.getAid()))) {
            throw new EntityExistsException("There is already existing entity with such ID in the database");
        }
        return adminDao.save(admin);
    }

    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    public Admin findOne(Integer id) {
        return adminDao.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        adminDao.deleteById(id);
    }

//    public Admin selectAdminnameByAid(int id) {return adminDao.findById(id).orElse(null).getUname();};


}
