package com.fashionblog.services;

import com.fashionblog.dtos.requestDto.PostDto;
import com.fashionblog.dtos.responseDto.PostResponseDto;

public interface PostService {
    PostResponseDto createPost(PostDto postDto);
    PostResponseDto getPostTitleById(Long postId);
    void deletePost(Long postId);
    PostResponseDto updatePost(PostDto postDto, Long postId);
}





//package com.fashionblog.services;
//
//public interface PostService {
//}
