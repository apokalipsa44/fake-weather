package com.michau.kurseasyapi.controller;

import com.michau.kurseasyapi.model.Post;
import com.michau.kurseasyapi.service.PostService;
import com.michau.kurseasyapi.service.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/page/{page}")
    public List<PostDto> getPosts(@PathVariable int page) {
        return postService.getPosts(page);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }
}
