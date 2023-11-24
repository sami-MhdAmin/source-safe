package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version,Long> {
}
