//package com.example.sourceSafeMaven.controller;
//
//import com.example.sourceSafeMaven.entities.Group;
//import com.example.sourceSafeMaven.entities.User;
//import com.example.sourceSafeMaven.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Set;
//
//@RestController
//@RequestMapping("/sourceSafe")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/files")
//    public Set<Group> getFiles() {
//        User user = userRepository.findById(1L)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + 1));
//
//        return user.getGroups();
//
//    }
////    @GetMapping("/files")
////    public String getFiles() {
////        return  "devtools";
////
////    }
//}
