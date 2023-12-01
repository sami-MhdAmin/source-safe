package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.TextFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextFileRepository extends JpaRepository<TextFile,Long> {

    List<TextFile> findAllByGroupId(Long groupId);
}
