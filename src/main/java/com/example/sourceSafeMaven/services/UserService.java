package com.example.sourceSafeMaven.services;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


//    public Optional<User> getFiles(Long userId) {
//        return userRepository.findById(userId);
//    }

    public List<Group> getFiles(Long userId) {
        List<User> users = userRepository.findAllById(userId);
        if (!users.isEmpty()) {
            User user = users.get(0);
            user.getGroups();
            return new ArrayList<>(user.getGroups());
        }
        return null;
    }
}
