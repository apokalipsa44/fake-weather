package com.michau.kurseasyapi.service;

import com.michau.kurseasyapi.model.Post;
import com.michau.kurseasyapi.repository.PostRepository;
import com.michau.kurseasyapi.service.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostService {

    private final int BUFFER_SIZE = 5;
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPosts(int page) {
        List<Post> posts = postRepository.findAllPosts(PageRequest.of(page, BUFFER_SIZE));
        List<PostDto> postDtos = posts.stream().map(post -> PostDto.builder()
                .id(post.getId()).content(post.getContent())
                .created(post.getCreated())
                .title(post.getTitle())
                .build()).collect(Collectors.toList());
        return postDtos;
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("error"));

    }
}
