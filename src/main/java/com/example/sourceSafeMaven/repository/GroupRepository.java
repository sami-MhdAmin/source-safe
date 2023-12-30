package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {

    boolean existsByIdAndUsersId(Long groupId, Long userId);
    List<Group> findByUsersNotContaining(User user);

    List<Group> findAll();
}
