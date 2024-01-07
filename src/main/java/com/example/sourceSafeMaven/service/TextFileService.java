package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.*;
import com.example.sourceSafeMaven.entities.enums.CheckOutStatus;
import com.example.sourceSafeMaven.entities.enums.ReservationStatus;
import com.example.sourceSafeMaven.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TextFileService {

    @Autowired
    private TextFileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;

    @Autowired
    GroupRepository groupRepository;
    public Map<String, Object> getFiles(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Set<Group> groups = user.getGroups();
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> groupList = new ArrayList<>();

        for (Group group : groups) {
            Map<String, Object> groupData = new HashMap<>();
            groupData.put("id", group.getId());
            groupData.put("name", group.getName());

            List<Map<String, Object>> fileList = new ArrayList<>();
            List<TextFile> files = group.getTextFiles();

            for (TextFile file : files) {
                List<Version> versions = file.getVersions();
                if (!versions.isEmpty()) {
                    Version lastVersion = versions.get(versions.size() - 1);
                    Map<String, Object> fileData = new HashMap<>();

                    byte[] fileContent = lastVersion.getFileContent();
                    String content = new String(fileContent);

                    fileData.put("fileName", file.getFileName());
                    fileData.put("lastVersion", content);
                    fileList.add(fileData);
                }
            }

            groupData.put("files", fileList);
            groupList.add(groupData);
        }

        response.put("groups", groupList);
        return response;
    }

    public List<TextFile> getFilesByGroupId(Long groupId) {
        return fileRepository.findAllByGroupId(groupId);
    }

    //When a user checks In a file, update the reservation status to RESERVED
    public ResponseEntity<String> checkInFile(Long userId, List<Long> fileIds) throws FileNotFoundException {
        boolean allFilesFree = true;

        for (Long fileId : fileIds) {
            TextFile file = fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));
            Group group = file.getGroup();
            if (groupRepository.existsByIdAndUsersId(group.getId(), userId)) {
                if (!file.getReservationStatus().equals(ReservationStatus.FREE)) {
                    allFilesFree = false;
                    break;
                }
            }
            else
            {
                allFilesFree = false;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("you haven't access to some or all file");

            }

        }
        if (allFilesFree) {
            for (Long fileId : fileIds) {
                TextFile file = fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));


                file.setReservationStatus(ReservationStatus.RESERVED);
                fileRepository.save(file);


                ReservationHistory reservationHistory = new ReservationHistory();
                reservationHistory.setTextFile(file);
                reservationHistory.setUser(userRepository.findById(userId).orElse(null));
                reservationHistoryRepository.save(reservationHistory);


            }
            return ResponseEntity.ok(fileIds.size() + "  Files Checked In Successfully");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some or all of the files you requested are already reserved");
        }
    }


    //When a user checks out a file, update the reservation status to FREE
    public ResponseEntity<String> checkOutFile(Long userId, Long fileId, MultipartFile file) throws IOException {
        TextFile textFile = fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));
        User user = userRepository.findById(userId).orElse(null);

        if (textFile.getReservationStatus() == ReservationStatus.RESERVED && reservationHistoryRepository.existsByUser_IdAndTextFile_IdAndCheckOutStatusIsNull(userId, textFile.getId())) {
            textFile.setReservationStatus(ReservationStatus.FREE);
            String content = new String(file.getBytes());
            Version version = new Version();
            version.setFileContent(content.getBytes());
            version.setUser(user);
            version.setTextFile(textFile);
            versionRepository.save(version);


            ReservationHistory reservationHistory = reservationHistoryRepository.findByTextFileIdAndCheckOutStatusNullAndCheckOutEndTimeNull(textFile.getId());

            reservationHistory.setCheckOutStatus(CheckOutStatus.UPDATE);
            reservationHistory.setCheckOutEndTime(LocalDateTime.now());
            reservationHistoryRepository.save(reservationHistory);
            fileRepository.save(textFile);
            return ResponseEntity.ok("  Files Checked Out Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there no check in for this file by this user");

        }

    }

}
