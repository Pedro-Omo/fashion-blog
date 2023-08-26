package com.fashionblog.controller;

import com.fashionblog.dtos.requestDto.CommentDto;
import com.fashionblog.dtos.requestDto.PostDto;
import com.fashionblog.dtos.responseDto.CommentResponseDto;
import com.fashionblog.dtos.responseDto.PostResponseDto;
import com.fashionblog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//    @PostMapping("/createComment")
//    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentDto commentDto,
//                                                            @RequestParam Long userId,
//                                                            @RequestParam Long postId) {
//        CommentResponseDto createdComment = commentService.createComment(commentDto, userId, postId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
//    }
    @PostMapping("/createComment/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId) {
        CommentResponseDto createdComment = commentService.createComment(commentDto, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

//    @PostMapping("/createPost")
//    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostDto postDto) {
//        PostResponseDto createdPost = postService.createPost(postDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
//    }

    @GetMapping("/{commentId}/getCommentById")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long commentId) {
        CommentResponseDto comment = commentService.getCommentById(commentId);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}/updateComment")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId,
                                                            @RequestBody CommentDto commentDto) {
        CommentResponseDto updatedComment = commentService.updateComment(commentDto, commentId);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}/deleteComment")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}




//package com.fashionblog.controller;
//
//public class CommentController {
//}
