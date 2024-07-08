package com.blogapp1.services;

import com.blogapp1.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);
}
