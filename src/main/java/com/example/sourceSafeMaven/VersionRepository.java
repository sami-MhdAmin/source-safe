package com.example.sourceSafeMaven;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version,Long> {
}
