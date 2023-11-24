package com.example.sourceSafeMaven;

import com.example.sourceSafeMaven.entities.Group;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@SpringBootApplication
public class SourceSafeMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceSafeMavenApplication.class, args);
    }

}
