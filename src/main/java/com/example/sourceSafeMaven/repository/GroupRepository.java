package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
