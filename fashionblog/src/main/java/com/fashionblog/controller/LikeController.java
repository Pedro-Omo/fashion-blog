package com.fashionblog.controller;

import com.fashionblog.services.LikeService;
import com.fashionblog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;
    private final PostService postService;

    public LikeController(LikeService likeService, PostService postService) {
        this.likeService = likeService;
        this.postService = postService;
    }

    @PostMapping("/likePost")
    public ResponseEntity<Void> likePost(@RequestParam Long userId, @RequestParam Long postId) {
        likeService.likePost(userId, postId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




    @DeleteMapping("/{likeId}/deleteLike")
    public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
        return ResponseEntity.noContent().build();
    }
}




//    @PostMapping("/likePost")
//    public ResponseEntity<Void> likePost(@RequestParam Long userId, @RequestParam Long postId) {
//        postService.likePost(userId, postId);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }




