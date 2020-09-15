package com.michau.kurseasyapi.repository;


import com.michau.kurseasyapi.model.Post;
import com.michau.kurseasyapi.service.dto.PostDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("Select p from Post p")
    List<Post> findAllPosts(Pageable pageable);
}
