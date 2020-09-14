package com.michau.kurseasyapi.service;

import com.michau.kurseasyapi.model.Post;
import com.michau.kurseasyapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAllPosts();
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));

    }
}
