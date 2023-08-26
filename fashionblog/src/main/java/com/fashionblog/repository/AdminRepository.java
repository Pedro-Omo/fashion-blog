package com.fashionblog.repository;

import com.fashionblog.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}




//package com.fashionblog.repository;
//
//public interface AdminRepository {
//}
