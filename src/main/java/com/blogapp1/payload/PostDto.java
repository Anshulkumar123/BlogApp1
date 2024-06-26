package com.blogapp1.payload;

import lombok.Data;

@Data
public class PostDto {
    private long id;
    private String title;
    private String descripation;
    private String content;
}
