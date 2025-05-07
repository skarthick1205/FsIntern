package com.example.cursor.based.pagination;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class PostFeedService {

    @Autowired
    private PostFeedRepository repository;

    public List<PostFeed> getPosts(Long afterCursor, int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize);

        if (afterCursor != null) {
            return repository.findNextPosts(afterCursor, pageable);
        } else {
            return repository.findInitialPosts(pageable);
        }
    }
}
