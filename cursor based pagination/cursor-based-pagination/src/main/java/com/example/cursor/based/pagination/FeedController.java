package com.example.cursor.based.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private PostFeedService service;

    @GetMapping
    public ResponseEntity<List<PostFeed>> getFeed(
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(defaultValue = "3") int size) {

        List<PostFeed> posts = service.getPosts(after, size);
        return ResponseEntity.ok(posts);
    }
}
