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

    @GetMapping("/post")
    public List<PostDto> getPosts(@RequestParam(required = false) int page) {
        int pageToDisplay = page >= 0 ? page : 0;
        return postService.getPosts(pageToDisplay);
    }

    @GetMapping("/post/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) int page) {
        int pageToDisplay = page >= 0 ? page : 0;
        return postService.getPostsWithComments(pageToDisplay);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }
}
