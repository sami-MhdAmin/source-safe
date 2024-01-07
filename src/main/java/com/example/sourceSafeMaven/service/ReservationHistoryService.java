package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.ReservationHistory;
import com.example.sourceSafeMaven.entities.TextFile;
import com.example.sourceSafeMaven.repository.GroupRepository;
import com.example.sourceSafeMaven.repository.ReservationHistoryRepository;
import com.example.sourceSafeMaven.repository.TextFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationHistoryService {
    @Autowired
    ReservationHistoryRepository reservationHistoryRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TextFileRepository textFileRepository;


    public List<ReservationHistory> historyOfUser(Long userId) {
        return reservationHistoryRepository.findByUserId(userId);
    }

    public ResponseEntity<?> historyOfFile(Long fileId, Long userId) {
        Optional<TextFile> textFile = textFileRepository.findById(fileId);
        Group group = textFile.get().getGroup();
        List<ReservationHistory> reservationHistories = reservationHistoryRepository.findByTextFileId(fileId);
        if (groupRepository.existsByIdAndUsersId(group.getId(), userId)) {
            if (reservationHistories != null) {
                return ResponseEntity.status(HttpStatus.OK).body(reservationHistories);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The File does not have any Check Ins");

            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("you haven't access to this file's history");

        }
    }
}
