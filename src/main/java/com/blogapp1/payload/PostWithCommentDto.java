package com.blogapp1.payload;

import com.blogapp1.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostWithCommentDto {
    private Post post;
    private List<CommentDto> commentsDto;
}
