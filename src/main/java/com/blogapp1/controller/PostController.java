package com.blogapp1.controller;

import com.blogapp1.payload.ListPostDto;
import com.blogapp1.payload.PostDto;
import com.blogapp1.services.PostService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody  PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/4
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is Deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/posts?pageN0=0&paageSize=5&sortBy=title&sortDir=asc
    @GetMapping
    public ResponseEntity<ListPostDto> fetchAllPost(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        ListPostDto listPostDto = postService.fetchAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(listPostDto, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

