package com.blogapp1.services;

import com.blogapp1.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    public List<CommentDto> getAllCommentsByPostId(long id);
}