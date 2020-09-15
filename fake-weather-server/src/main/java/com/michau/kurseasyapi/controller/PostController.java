package com.michau.kurseasyapi.controller;

import com.michau.kurseasyapi.model.Post;
import com.michau.kurseasyapi.service.PostService;
import com.michau.kurseasyapi.service.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageToDisplay = page >= 0 && page!=null ? page : 0;
        Sort.Direction sortToDisplay=sort!=null?sort: Sort.Direction.ASC;
        return postService.getPosts(pageToDisplay, sortToDisplay);
    }

    @GetMapping("/post/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page) {
        int pageToDisplay = page >= 0 && page!=null ? page : 0;
        return postService.getPostsWithComments(pageToDisplay);
    }

    @GetMapping("/post/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }

    @PostMapping("/post")
    public Post savePost(@RequestBody Post post){
        return postService.save(post);
    }

}
