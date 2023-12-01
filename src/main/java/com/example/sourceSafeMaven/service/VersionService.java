package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.TextFile;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.entities.Version;
import com.example.sourceSafeMaven.repository.GroupRepository;
import com.example.sourceSafeMaven.repository.TextFileRepository;
import com.example.sourceSafeMaven.repository.UserRepository;
import com.example.sourceSafeMaven.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

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
                textFileRepository.save(fileVersion);

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


    public byte[] getVersionBytes(Long versionId) throws FileNotFoundException {
        Version versionx = versionRepository.findById(versionId)
                .orElseThrow(() -> new FileNotFoundException("File not found"));

        byte[] version = versionx.getFileContent();
        return version;
    }


}
