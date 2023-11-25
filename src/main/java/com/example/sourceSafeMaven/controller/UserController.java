package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.repository.UserRepository;
import com.example.sourceSafeMaven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sourceSafe")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/files")
//    public ResponseEntity<List<Group>> getFiles() {
//        List<Group> files = userService.getFiles();
//        return ResponseEntity.ok(files);
//    }

    @GetMapping("/files")
    public ResponseEntity<User> getFiles() {
        User groups = userService.getFiles();

            return ResponseEntity.ok(groups);

        }
    }

}
