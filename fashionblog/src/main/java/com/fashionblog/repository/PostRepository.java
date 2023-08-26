package com.fashionblog.repository;

import com.fashionblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAdminId(Long userId);
//    List<Post> findByCategory(String category);
//    List<Post> findByUserIdAndCategory(Long userId, String category);

}





