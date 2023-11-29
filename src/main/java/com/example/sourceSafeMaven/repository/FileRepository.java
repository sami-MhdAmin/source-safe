package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.TextFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<TextFile,Long> {
}
