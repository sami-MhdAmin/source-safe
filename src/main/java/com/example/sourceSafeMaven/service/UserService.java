package com.example.sourceSafeMaven.service;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.repository.GroupRepository;
import com.example.sourceSafeMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            if(isUserInGroup)
            {

            }
            else
            {

            }
        }
        return new ResponseEntity<>("!", HttpStatus.BAD_REQUEST);

    }

    public Optional<User> getFiles(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }
}
