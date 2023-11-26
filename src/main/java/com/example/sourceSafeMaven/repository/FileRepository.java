package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.FileVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileVersion,Long> {
}
