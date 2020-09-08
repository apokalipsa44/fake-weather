package com.michau.kurseasyapi.controller;


import com.michau.kurseasyapi.models.Post;
import com.michau.kurseasyapi.services.PostServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private PostServices postServices;

    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping("posts")
    public List<Post> getPosts() {
        return postServices.getAllPosts();
    }

}
