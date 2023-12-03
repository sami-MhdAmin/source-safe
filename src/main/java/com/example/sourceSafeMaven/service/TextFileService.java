package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.*;
import com.example.sourceSafeMaven.models.FileAlreadyReservedException;
import com.example.sourceSafeMaven.models.FileNotReservedByUserException;
import com.example.sourceSafeMaven.repository.TextFileRepository;
import com.example.sourceSafeMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.*;

@Service
public class TextFileService {

    @Autowired
    private TextFileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;


    //When a user checks In a file, update the reservation status to RESERVED
    public void checkInFile(Long fileId, Long userId) throws FileNotFoundException {
        // Fetch the file from the database
        TextFile file = fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));

        // Check if the file is currently free
        if (file.getReservationStatus() == ReservationStatus.FREE) {
            // Reserve the file for the user
            file.setReservedByUserId(userId);
            file.setReservationStatus(ReservationStatus.RESERVED);

            // Other check-out logic...

            // Save the updated file to the database
            fileRepository.save(file);
        } else {
            // Handle case where the file is already reserved
            throw new FileAlreadyReservedException("File is already reserved by another user");
        }
    }

    //When a user checks out a file, update the reservation status to FREE
    public void checkOutFile(Long fileId,Long loggedInUserId) throws FileNotFoundException {
        // Fetch the file from the database
        TextFile file = fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found"));

        // Check if the file is currently reserved by the user
        if (file.getReservationStatus() == ReservationStatus.RESERVED && file.getReservedByUserId().equals(loggedInUserId)) {
//            file.getReservedByUserId():
//            This method is assumed to retrieve the user ID of the person who currently has the file reserved.
//            This could be a method in your File entity that returns the user ID associated with the reservation.
//
//            loggedInUserId:
//            This is likely a variable or property representing the user ID of the person who is currently logged into the system.

            // Release the reservation
            file.setReservedByUserId(null);
            file.setReservationStatus(ReservationStatus.FREE);

            // Other check-in logic...

            // Save the updated file to the database
            fileRepository.save(file);
        } else {
            // Handle case where the file is not reserved by the user
            throw new FileNotReservedByUserException("File is not reserved by the current user");
        }
    }


    public Map<String, Object> getFiles(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Set<Group> groups = user.getGroups();
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> groupList = new ArrayList<>();

        for (Group group : groups) {
            Map<String, Object> groupData = new HashMap<>();
            groupData.put("id", group.getId());

            List<Map<String, Object>> fileList = new ArrayList<>();
            List<TextFile> files = group.getFiles();

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

}
