package com.fashionblog.services.Implementation;

import com.fashionblog.entities.Post;
import com.fashionblog.entities.UserTable;
import com.fashionblog.exceptions.CustomAppException;
import com.fashionblog.exceptions.ResourceNotFoundException;
import com.fashionblog.repository.LikeRepository;
import com.fashionblog.repository.PostRepository;
import com.fashionblog.repository.UserRepository;
import com.fashionblog.services.LikeService;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImplementation implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeServiceImplementation(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }



    @Override
    public void likePost(Long userId, Long postId) {
        UserTable user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (user.getLikedPosts().contains(post)) {
            throw new CustomAppException("You have already liked this post");
        }

        user.getLikedPosts().add(post);
        userRepository.save(user);
    }


    @Override
    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}






//package com.fashionblog.services.Implementation;
//
//public class LikeServiceImplementation {
//}
