package com.fashionblog.repository;

import com.fashionblog.entities.LikeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeTable, Long> {
    List<LikeTable> findByPostId(Long postId);
    // Add more custom query methods if needed
}




//package com.fashionblog.repository;
//
//public interface LikeRepository {
//}
