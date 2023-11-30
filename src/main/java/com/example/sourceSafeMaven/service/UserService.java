package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.*;
import com.example.sourceSafeMaven.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private VersionRepository versionRepository;

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
                fileRepository.save(fileVersion);

                version.setFile(fileVersion);

                versionRepository.save(version);
                return ResponseEntity.ok("File uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user is not in the group");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
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

}