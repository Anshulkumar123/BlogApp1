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

        Post post = MapToEntity(postDto);

        Post savedPost = postRepository.save(post);

        PostDto postDto1 = MapToEntity(savedPost);
        return postDto1;
    }

    Post MapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescripation(postDto.getDescripation());
        post.setContent(postDto.getContent());
        return post;
    }

    PostDto MapToEntity(Post post){
        PostDto postDto1 = new PostDto();
        postDto1.setTitle(postDto1 .getTitle());
        postDto1.setDescripation(postDto1 .getDescripation());
        postDto1.setContent(postDto1 .getContent());
        return postDto1;
    }
}
