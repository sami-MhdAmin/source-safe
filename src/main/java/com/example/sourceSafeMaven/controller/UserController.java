package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.dto.AddFileDto;
import com.example.sourceSafeMaven.security.JwtService;
import com.example.sourceSafeMaven.service.TextFileService;
import com.example.sourceSafeMaven.service.UserService;
import com.example.sourceSafeMaven.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/getToken")
    public ResponseEntity<Long> getToken(@RequestHeader HttpHeaders headers) {
        Long userId = jwtService.getUserIdByToken(headers);
        System.out.println(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }



//    @GetMapping("/user")
//    public User getUserFromToken(@RequestHeader("Authorization") String token) {
//        // Assuming the Authorization header contains a Bearer token
//        String jwtToken = token.replace("Bearer ", "");
//        return jwtService.extractUser(jwtToken);
//    }

//    @GetMapping("/user/id")
//    public String getUserIdFromToken(@RequestHeader("Authorization") String token) {
//        System.out.println("hi I am sami1");
//        // Assuming the Authorization header contains a Bearer token
//        System.out.println(token);
//        String jwtToken = token.replace("Bearer ", "");
//        System.out.println("hi I am sami2");
//        System.out.println(jwtToken);
//        String userId = jwtService.extractUserId(jwtToken);
//        System.out.println("hi I am sami3");
//
//        System.out.println(userId);
//        return userId;
//    }
}