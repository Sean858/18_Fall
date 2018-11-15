package com.forum.Controller;

import com.forum.Entity.Post;
import com.forum.Service.PostService;
import com.forum.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }


    @RequestMapping(value="post", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getAllPostsByTime(){
        List<Post> reversePost = new ArrayList<Post>();
        Stack<Post> orderPost = new Stack<>();

        for (Post post : postService.findAll()) {
            if(post.getFlag() == 0) {
                orderPost.push(post);
            }
        }
        while(!orderPost.isEmpty()) {
            reversePost.add(orderPost.pop());
        }
        return reversePost;
    }

    @RequestMapping(value="post", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws URISyntaxException {
        try{
            Post result = postService.save(post);
            return ResponseEntity.created(new URI("/api/post" + result.getPid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<Post>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value="post", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> updatepost(@RequestBody Post post) throws URISyntaxException{
        if(post.getPid() == null){
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }

        try{
            Post result = postService.update(post);
            return ResponseEntity.created(new URI("/api/post" + result.getPid())).body(result);
        } catch (EntityExistsException e) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/post/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletepost(@PathVariable Integer id){
        postService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="post/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String selectPostTitleByPid(@PathVariable Integer id) {
        return postService.findOne(id).getTitle();

    }

}
