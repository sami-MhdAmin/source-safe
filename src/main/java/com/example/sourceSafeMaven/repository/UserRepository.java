package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameOrEmail(String userName, String email);

    Optional<User> findByEmail(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    List<User> findUsersByIdIn(List<Long> ids);

    List<User> findByGroupsNotContaining(Group group);


}
