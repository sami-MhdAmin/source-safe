package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/sourceSafe")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("addFile/{groupId}")
    public ResponseEntity<String> addFile(@PathVariable("groupId") Long groupId)
    {
        Long userId= Long.valueOf(1);
        userService.addFile(groupId,userId);
        return new ResponseEntity<>("file added successfully", HttpStatus.OK);
    }

    @GetMapping("/files/{userId}")
    public Optional<User> getFiles(@PathVariable("userId") Long Id) {
        return userService.getFiles(Id);
    }
}
