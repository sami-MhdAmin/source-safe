package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.dto.file.CheckOutFileDto;
import com.example.sourceSafeMaven.entities.ReservationHistory;
import com.example.sourceSafeMaven.repository.ReservationHistoryRepository;
import com.example.sourceSafeMaven.security.JwtService;
import com.example.sourceSafeMaven.service.TextFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/textFile")
public class TextFileController {


    @Autowired
    private TextFileService textFileService;
    @Autowired
    private JwtService jwtService;


    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;

    @PostMapping("/checkIn")
    public ResponseEntity<String> checkIn(@RequestBody List<Long> fileIds, @RequestHeader HttpHeaders httpHeaders) {
        Long userId = jwtService.getUserIdByToken(httpHeaders);
        try {
            return textFileService.checkInFile(userId, fileIds);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/checkOut")
    public ResponseEntity<String> checkOut(@ModelAttribute CheckOutFileDto request, @RequestHeader HttpHeaders httpHeaders) {

        Long userId = jwtService.getUserIdByToken(httpHeaders);
        try {
            return textFileService.checkOutFile(userId, request.getFileId(), request.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
