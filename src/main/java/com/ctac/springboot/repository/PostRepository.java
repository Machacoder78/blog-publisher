package com.ctac.springboot.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ctac.springboot.models.Post;

import java.util.*;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author ORDER BY p.date DESC")
    List <Post> findLatest5Posts(PageRequest pageRequest);
    
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword% ORDER BY p.date DESC")
    List<Post> searchPosts(@Param("keyword") String keyword);
}

