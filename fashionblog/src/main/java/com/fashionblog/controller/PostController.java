package com.fashionblog.controller;

import com.fashionblog.dtos.requestDto.PostDto;
import com.fashionblog.dtos.responseDto.PostResponseDto;
import com.fashionblog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostDto postDto) {
        PostResponseDto createdPost = postService.createPost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{postId}/getPostById")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId) {
        PostResponseDto post = postService.getPostTitleById(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}/updatePost")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        PostResponseDto updatedPost = postService.updatePost(postDto, postId);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}/deletePost")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}





