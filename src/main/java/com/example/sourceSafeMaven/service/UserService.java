package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.FileVersion;
import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.entities.Version;
import com.example.sourceSafeMaven.repository.FileRepository;
import com.example.sourceSafeMaven.repository.GroupRepository;
import com.example.sourceSafeMaven.repository.UserRepository;
import com.example.sourceSafeMaven.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

                FileVersion fileVersion = new FileVersion();
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

//    public List<FileVersion> getFiles(Long userId) {
//        Optional<User> userOpt = userRepository.findById(userId);
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            Collections response = (Collections) user.getGroups();
//            List<Group> groups = (List<Group>) user.getGroups();
//            List<FileVersion> filesWithVersions = new ArrayList<>();
//
//            for (Group group : groups) {
//                List<FileVersion> files = group.getFiles();
//                for (FileVersion file : files) {
//                    List<Version> versions = file.getVersions();
//                    if (!versions.isEmpty()) {
//                        FileVersion fileWithVersions = new FileVersion();
//                        fileWithVersions.setVersions(versions);
//                        filesWithVersions.add(fileWithVersions);
//                    }
//                }
//            }
//
//            return filesWithVersions;
//        }
//
//        return null;
//    }
//

    public List<Group> getFiles() {
        return groupRepository.findAll();
    }

}
