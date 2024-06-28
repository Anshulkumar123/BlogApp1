package com.blogapp1.controller;

import com.blogapp1.payload.PostDto;
import com.blogapp1.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody  PostDto postDto){
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //http://localhost:8080/api/posts/4
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is Deleted", HttpStatus.OK);
    }
}

