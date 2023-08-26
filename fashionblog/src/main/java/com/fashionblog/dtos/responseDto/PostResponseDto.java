package com.fashionblog.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String content;
    private Long adminId;
    private Integer likeCount;
}




//package com.fashionblog.dtos.responseDto;
//
//public class PostResponseDto {
//}
