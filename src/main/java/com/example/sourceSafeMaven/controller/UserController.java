package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.entities.FileVersion;
import com.example.sourceSafeMaven.security.JwtService;
import com.example.sourceSafeMaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sourceSafe")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/addFile")
    public String addFile(@RequestHeader("Authorization") String authorizationHeader) {
//        String token = authorizationHeader.substring(7);
//       Claims user = jwtService.extractId(token);
//        return user;
        return "ffff";
    }

    @GetMapping("/files/{userId}")
    public List<FileVersion> getFiles(@PathVariable("userId") Long userId) {
        return userService.getFiles(userId);
    }
}
