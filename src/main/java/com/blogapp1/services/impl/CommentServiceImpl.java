package com.blogapp1.services.impl;

import com.blogapp1.entity.Comment;
import com.blogapp1.entity.Post;
import com.blogapp1.payload.CommentDto;
import com.blogapp1.reposiotry.CommentRepository;
import com.blogapp1.reposiotry.PostRepository;
import com.blogapp1.services.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.provider.QueryComment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    public List<CommentDto> getAllCommentsByPostId(long id){
        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    Comment mapToEntity(CommentDto dto){
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
}
