package com.michau.kurseasyapi.repos;

import com.michau.kurseasyapi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("Select p from Post p left join fetch p.comment")
    List<Post> findAllPosts();

}