package com.blogapp1.services;

import com.blogapp1.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletePost(long id);

    List<PostDto> fetchAllPost();
}
