package com.fashionblog.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String text;
    private Long userId;
    private Long postId;
}




//package com.fashionblog.dtos.responseDto;
//
//public class CommentResponseDto {
//}
