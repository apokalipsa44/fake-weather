package com.michau.kurseasyapi.repos;

import com.michau.kurseasyapi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface PostRepository extends JpaRepository<Post, Long> {


}
