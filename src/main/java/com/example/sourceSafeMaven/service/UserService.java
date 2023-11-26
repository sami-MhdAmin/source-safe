package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.FileVersion;
import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.entities.Version;
import com.example.sourceSafeMaven.repository.GroupRepository;
import com.example.sourceSafeMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public ResponseEntity<String> addFile(Long groupId, Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        if (userOpt.isPresent() && groupOpt.isPresent()) {
            User user = userOpt.get();
            Group group = groupOpt.get();
            boolean isUserInGroup = user.getGroups().contains(group);
            if (isUserInGroup) {
                FileVersion fileVersion=new FileVersion();
                fileVersion.setGroup(group);
                Version version=new Version();
                version.setUser(user);
                version.setFile(fileVersion);
                version.setFileContent();

            } else {
                return new ResponseEntity<>("you are not member in this group", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("!", HttpStatus.BAD_REQUEST);

    }

    public List<FileVersion> getFiles(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<Group> groups = (List<Group>) user.getGroups();

            List<FileVersion> files = new ArrayList<>();
            for (Group group : groups) {
                files.addAll(group.getFiles());
            }

            return files;
        }
        return Collections.emptyList();
    }
}
