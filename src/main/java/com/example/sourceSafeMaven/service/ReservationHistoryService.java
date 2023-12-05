package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.ReservationHistory;
import com.example.sourceSafeMaven.repository.ReservationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationHistoryService {
    @Autowired
    ReservationHistoryRepository reservationHistoryRepository;


    public List<ReservationHistory> historyOfUser(Long userId) {
        return reservationHistoryRepository.findByUserId(userId);
    }
    public List<ReservationHistory> historyOfFile(Long fileId) {
        return reservationHistoryRepository.findByTextFileId(fileId);
    }
}
