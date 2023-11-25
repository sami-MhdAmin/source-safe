package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameOrEmail(String userName, String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
