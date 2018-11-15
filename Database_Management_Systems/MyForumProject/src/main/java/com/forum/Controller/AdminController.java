package com.forum.Controller;

import com.forum.Entity.Admin;
import com.forum.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }


    @RequestMapping(value="admin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Admin> getAlladmins(){
        return adminService.findAll();
    }

    @RequestMapping(value="admin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) throws URISyntaxException {
        try{
            Admin result = adminService.save(admin);
            return ResponseEntity.created(new URI("/api/admin" + result.getAid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<Admin>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="admin", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> updateadmin(@RequestBody Admin admin) throws URISyntaxException{
        if(admin.getAid() == null){
            return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
        }

        try{
            Admin result = adminService.update(admin);
            return ResponseEntity.created(new URI("/api/admin" + result.getAid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/admin/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteadmin(@PathVariable Integer id){
        adminService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="admin/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Admin selectAdminnameByUid(@PathVariable Integer id) {
        return adminService.findOne(id);

    }


    @RequestMapping(value="admin/{id}/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectAdminnameByAid(@PathVariable Integer id) {
        return adminService.findOne(id).getAname();

    }

    @RequestMapping(value="admin/{id}/email", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectAdminemailByAid(@PathVariable Integer id) {
        return adminService.findOne(id).getAemail();

    }



}
