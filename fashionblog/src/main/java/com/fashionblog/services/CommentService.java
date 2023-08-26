package com.fashionblog.services;

import com.fashionblog.dtos.requestDto.CommentDto;
import com.fashionblog.dtos.responseDto.CommentResponseDto;

public interface CommentService {
//    CommentResponseDto createComment(CommentDto commentDto, Long userId, Long postId);
CommentResponseDto createComment(CommentDto commentDto, Long postId);
    CommentResponseDto getCommentById(Long commentId);
    void deleteComment(Long commentId);
    CommentResponseDto updateComment(CommentDto commentDto, Long commentId);
}





//package com.fashionblog.services;
//
//public interface CommentService {
//}
