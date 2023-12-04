package com.example.sourceSafeMaven.repository;

import com.example.sourceSafeMaven.entities.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationHistoryRepository extends JpaRepository<ReservationHistory, Long> {
}
