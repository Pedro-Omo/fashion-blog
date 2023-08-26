package com.fashionblog.services.Implementation;

import com.fashionblog.dtos.requestDto.PostDto;
import com.fashionblog.dtos.responseDto.PostResponseDto;
import com.fashionblog.entities.Admin;
import com.fashionblog.entities.Post;
import com.fashionblog.exceptions.ResourceNotFoundException;
import com.fashionblog.repository.AdminRepository;
import com.fashionblog.repository.PostRepository;
import com.fashionblog.services.PostService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    private final AdminRepository adminRepository;
    private final HttpSession session;

    public PostServiceImplementation(PostRepository postRepository, AdminRepository adminRepository, HttpSession session) {
        this.postRepository = postRepository;
        this.adminRepository = adminRepository;
        this.session = session;
    }

    @Override
    public PostResponseDto createPost(PostDto postDto) {
        Long adminId = (Long) session.getAttribute("adminId");
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setAdmin(admin);
        post.setTimestamp(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        return mapToPostResponseDto(savedPost);
    }

    @Override
    public PostResponseDto getPostTitleById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        return mapToPostResponseDto(post);
    }

    @Override
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post not found");
        }

        postRepository.deleteById(postId);
    }

    @Override
    public PostResponseDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToPostResponseDto(updatedPost);
    }

    private PostResponseDto mapToPostResponseDto(Post post) {
        return PostResponseDto.builder()
                .postId(post.getId())
                .content(post.getContent())
                .adminId(post.getAdmin().getId())
                .build();
    }



//    @Override
//    public List<PostResponseDto> getAllPosts() {
//        List<Post> posts = postRepository.findAll();
//
//        return posts.stream()
//                .map(this::mapToPostResponseDto)
//                .collect(Collectors.toList());
//    }
}




//package com.fashionblog.services.Implementation;
//
//public class PostServiceImplementation {
//}
