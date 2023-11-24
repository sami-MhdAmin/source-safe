package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
