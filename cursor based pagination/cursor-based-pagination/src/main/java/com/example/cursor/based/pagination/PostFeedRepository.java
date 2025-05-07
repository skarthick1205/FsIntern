package com.example.cursor.based.pagination;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PostFeedRepository extends JpaRepository<PostFeed, Long> {

    @Query("SELECT p FROM PostFeed p WHERE p.id < :cursor ORDER BY p.id DESC")
    List<PostFeed> findNextPosts(@Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT p FROM PostFeed p ORDER BY p.id DESC")
    List<PostFeed> findInitialPosts(Pageable pageable);
}

