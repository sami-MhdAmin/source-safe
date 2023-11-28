package com.example.sourceSafeMaven.controller;

import com.example.sourceSafeMaven.dto.AddFileDto;
import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.repository.GroupRepository;
import com.example.sourceSafeMaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sourceSafe")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupRepository groupRepository;
    @PostMapping("/addFile")
    public ResponseEntity<String> addFile(@ModelAttribute  AddFileDto request) {
        if (!request.getFile().isEmpty()) {
            return userService.addFile( 1L, request.getFile(),request.getGroupId(),request.getFileName());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @GetMapping("/files")
    public List<Group> getFiles() {
        return userService.getFiles();
    }


    @GetMapping("/filess")
    public String testString() {
        return "go to hell";
    }
}
