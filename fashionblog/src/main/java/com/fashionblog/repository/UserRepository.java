package com.fashionblog.repository;

import com.fashionblog.entities.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
    Optional<UserTable> findByEmail(String email);
    // Add more custom query methods if needed
}




//package com.fashionblog.repository;
//
//public interface UserRepository {
//}
