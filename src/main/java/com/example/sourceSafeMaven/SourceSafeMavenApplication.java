package com.example.sourceSafeMaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/sourceSafe")
@SpringBootApplication
public class SourceSafeMavenApplication {
    private final UserRepository userRepository;

    public SourceSafeMavenApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SourceSafeMavenApplication.class, args);
    }

    @GetMapping("/files")
    public Set<Group> getFiles() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + 1));

        return user.getGroups();

    }
//    @GetMapping("/files")
//    public String getFiles() {
//        return  "devtools";
//
//    }
}
