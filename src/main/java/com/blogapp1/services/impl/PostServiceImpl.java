package com.blogapp1.services.impl;

import com.blogapp1.entity.Post;
import com.blogapp1.exception.ResourceNotFound;
import com.blogapp1.payload.ListPostDto;
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
    public ListPostDto fetchAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
//        List<Post> post = postRepository.findAll();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> all = postRepository.findAll(pageable);
        List<Post> post = all.getContent();
        List<PostDto> posts = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        ListPostDto listPostDto = new ListPostDto();
        listPostDto.setPostDto(posts);
        listPostDto.setTotalPages(all.getTotalPages());
        listPostDto.setTotalElements((int) all.getTotalElements());
        listPostDto.setFirstPage(all.isFirst());
        listPostDto.setLastPage(all.isLast());
        listPostDto.setPageNumber(all.getNumber());
        return listPostDto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Post Not found with id :"+ id)
        );
        return mapToDto(post);
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
