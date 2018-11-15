package com.forum.Controller;

import com.forum.Entity.User;
import com.forum.Service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping("/hi")
    public String hi() {
        return "Hello World from Restful API";
    }

    @RequestMapping(value="user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllusers(){
        return userService.findAll();
    }

    @RequestMapping(value="user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        try{
            User result = userService.save(user);
            return ResponseEntity.created(new URI("/api/user" + result.getUid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateuser(@RequestBody User user) throws URISyntaxException{
        if(user.getUid() == null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        try{
            User result = userService.update(user);
            return ResponseEntity.created(new URI("/api/user" + result.getUid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteuser(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectUsernameByUid(@PathVariable Integer id) {
        return userService.findOne(id).getUname();

    }



}
