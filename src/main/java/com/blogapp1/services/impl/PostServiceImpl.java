package com.blogapp1.services.impl;

import com.blogapp1.entity.Post;
import com.blogapp1.payload.PostDto;
import com.blogapp1.reposiotry.PostRepository;
import com.blogapp1.services.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescripation(postDto.getDescripation());
        post.setContent(postDto.getContent());
        Post savedPost = postRepository.save(post);

        PostDto postDto1 = new PostDto();
        postDto1.setTitle(savedPost.getTitle());
        postDto1.setDescripation(savedPost.getDescripation());
        postDto1.setContent(savedPost.getContent());
        return postDto1;
    }
}
