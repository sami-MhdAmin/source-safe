package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
