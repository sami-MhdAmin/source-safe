package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long> {
    ReservationHistory findByTextFileIdAndCheckOutStatusNullAndCheckOutEndTimeNull(Long textFileId);

    List<ReservationHistory> findByExpirationTimeBeforeAndCheckOutStatusIsNull(LocalDateTime currentTime);

    List<ReservationHistory> findByUserId(Long userId);
    List<ReservationHistory>  findByTextFileId(Long textFileId);

    boolean existsByUser_IdAndTextFile_IdAndCheckOutStatusIsNull(Long userId, Long textFileId);

}
