package com.blogapp1.services;

import com.blogapp1.payload.ListPostDto;
import com.blogapp1.payload.PostDto;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    void deletePost(long id);

    ListPostDto fetchAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
