package com.fashionblog.services.Implementation;

import com.fashionblog.dtos.requestDto.CommentDto;
import com.fashionblog.dtos.requestDto.PostDto;
import com.fashionblog.dtos.responseDto.CommentResponseDto;
import com.fashionblog.dtos.responseDto.PostResponseDto;
import com.fashionblog.entities.Admin;
import com.fashionblog.entities.Comment;
import com.fashionblog.entities.Post;
import com.fashionblog.entities.UserTable;
import com.fashionblog.exceptions.ResourceNotFoundException;
import com.fashionblog.repository.CommentRepository;
import com.fashionblog.repository.PostRepository;
import com.fashionblog.repository.UserRepository;
import com.fashionblog.services.CommentService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class CommentServiceImplementation implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final HttpSession session;

    public CommentServiceImplementation(CommentRepository commentRepository,
                                        UserRepository userRepository,
                                        PostRepository postRepository, HttpSession session) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.session = session;
    }

//    @Override
//    public CommentResponseDto createComment(CommentDto commentDto, Long userId, Long postId) {
//        UserTable userTable = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("UserTable not found"));
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
//
//        Comment comment = new Comment();
//        comment.setText(commentDto.getText());
//        comment.setUserTable(userTable);
//        comment.setPost(post);
//
//        Comment savedComment = commentRepository.save(comment);
//
//        return mapToCommentResponseDto(savedComment);
//    }
@Override
public CommentResponseDto createComment(CommentDto commentDto, Long postId) {
    Long userId = (Long) session.getAttribute("UserId");
    UserTable userTable = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("UserTable not found"));

    // Retrieve the Post using some identifier, e.g., postId from commentDto
    Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

    Comment comment = new Comment();
    comment.setText(commentDto.getText());
    comment.setUserTable(userTable);
    comment.setPost(post); // Set the Post for the Comment

    Comment savedComment = commentRepository.save(comment);

    return mapToCommentResponseDto(savedComment);
}

//    @Override
//    public PostResponseDto createPost(PostDto postDto) {
//        Long adminId = (Long) session.getAttribute("adminId");
//        Admin admin = adminRepository.findById(adminId)
//                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
//
//        Post post = new Post();
//        post.setContent(postDto.getContent());
//        post.setAdmin(admin);
//        post.setTimestamp(LocalDateTime.now());
//
//        Post savedPost = postRepository.save(post);
//
//        return mapToPostResponseDto(savedPost);
//    }

    @Override
    public CommentResponseDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        return mapToCommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found");
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentResponseDto updateComment(CommentDto commentDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        comment.setText(commentDto.getText());

        Comment updatedComment = commentRepository.save(comment);

        return mapToCommentResponseDto(updatedComment);
    }

    private CommentResponseDto mapToCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .text(comment.getText())
                .userId(comment.getUserTable().getId())
                .postId(comment.getPost().getId())
                .build();
    }

//    @Override
//    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
//        List<Comment> comments = commentRepository.findByPostId(postId);
//        return comments.stream()
//                .map(this::mapToCommentResponseDto)
//                .collect(Collectors.toList());
//    }
}
