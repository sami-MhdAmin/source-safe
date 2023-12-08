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
public class VersionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TextFileRepository textFileRepository;

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;


    public ResponseEntity<String> addFile(Long userId, MultipartFile file, Long groupId, String fileName) {
        try {
            if (groupRepository.existsByIdAndUsersId(groupId, userId)) {
                String content = new String(file.getBytes());
                Version version = new Version();
                version.setFileContent(content.getBytes());

                Optional<User> userOptional = userRepository.findById(userId);
                User user = userOptional.orElse(null);
                version.setUser(user);

                Optional<Group> groupOptional = groupRepository.findById(groupId);
                Group group = groupOptional.orElse(null);
                //create file
                TextFile fileVersion = new TextFile();
                fileVersion.setGroup(group);
                fileVersion.setFileName(fileName);
                fileVersion.setReservationStatus(ReservationStatus.FREE);
                textFileRepository.save(fileVersion);

                version.setTextFile(fileVersion);
                versionRepository.save(version);

                ReservationHistory reservationHistory = new ReservationHistory();
                reservationHistory.setTextFile(fileVersion);
                reservationHistory.setUser(user);
                reservationHistory.setCheckOutStatus(CheckOutStatus.UPLOAD);
                reservationHistory.setCheckOutEndTime(LocalDateTime.now());
                reservationHistoryRepository.save(reservationHistory);


                return ResponseEntity.ok("File uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user is not in the group");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }


    public byte[] getVersionBytes(Long versionId) throws FileNotFoundException {
        Version versionx = versionRepository.findById(versionId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));

        byte[] version = versionx.getFileContent();
        return version;
    }


    public byte[] download(Long userId, Long fileId) throws FileNotFoundException {
        TextFile textFile = textFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        if (textFile.getReservationStatus() == ReservationStatus.RESERVED && reservationHistoryRepository.existsByUser_IdAndTextFile_IdAndCheckOutStatusIsNull(userId, textFile.getId())) {

            List<Version> versions = textFile.getVersions();
            Version lastVersion = null;
            if (!versions.isEmpty()) {
                lastVersion = versions.get(versions.size() - 1);
            }
            return lastVersion.getFileContent();
        }
        return null;
    }

    public List<Map<String, Object>> fileVersions(Long userId, Long fileId) throws FileNotFoundException {
        TextFile textFile = textFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));
        if (textFile.getReservationStatus() == ReservationStatus.RESERVED && reservationHistoryRepository.existsByUser_IdAndTextFile_IdAndCheckOutStatusIsNull(userId, textFile.getId())) {
            List<Version> versions = textFile.getVersions();
            List<Map<String, Object>> responseList = new ArrayList<>();
            for (Version version : versions) {
                Map<String, Object> versionData = new HashMap<>();
                versionData.put("id", version.getId());
                byte[] fileContent = version.getFileContent();
                String content = new String(fileContent);
                versionData.put("fileContent", content);
                responseList.add(versionData);
            }
            return responseList;
        }
        return null;
    }

}
