package com.michau.kurseasyapi.services;

import com.michau.kurseasyapi.models.Post;
import com.michau.kurseasyapi.repos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServices {

    private PostRepository postsRepository;

    public PostServices(PostRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    public List<Post> getAppPostsWithQuery() {
        System.out.println("query");
        return postsRepository.findAllPosts();
    }

    public List<Post> getPostsByTitle(String title){
        return postsRepository.findPostsByTitle(title);
    }

}
