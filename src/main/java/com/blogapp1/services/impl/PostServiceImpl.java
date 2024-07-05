package com.blogapp1.services.impl;

import com.blogapp1.entity.Post;
import com.blogapp1.payload.PostDto;
import com.blogapp1.reposiotry.PostRepository;
import com.blogapp1.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper= modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = MapToEntity(postDto);

        Post savedPost = postRepository.save(post);

        PostDto postDto1 = mapToDto(savedPost);
        return postDto1;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> fetchAllPost(int pageNo, int pageSize, String sortBy) {
//        List<Post> post = postRepository.findAll();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> all = postRepository.findAll(pageable);
        List<Post> post = all.getContent();
        List<PostDto> posts = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return posts;
    }

    Post MapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    PostDto mapToDto(Post post){
        PostDto postDto1 = modelMapper.map(post, PostDto.class);
        return postDto1;
    }
}
