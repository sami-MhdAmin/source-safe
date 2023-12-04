package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.List;

public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long> {
    ReservationHistory findByTextFileIdAndCheckOutStatusNull(Long textFileId);
}
