package com.example.sourceSafeMaven;

import com.example.sourceSafeMaven.entities.Group;
//import com.example.sourceSafeMaven.entities.Role;
import com.example.sourceSafeMaven.entities.Role;
import com.example.sourceSafeMaven.entities.User;
//import com.example.sourceSafeMaven.repository.RoleRepository;
import com.example.sourceSafeMaven.models.AuthenticationResponse;
import com.example.sourceSafeMaven.repository.GroupRepository;
import com.example.sourceSafeMaven.repository.TextFileRepository;
import com.example.sourceSafeMaven.repository.UserRepository;
import com.example.sourceSafeMaven.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SpringBootApplication
public class SourceSafeMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceSafeMavenApplication.class, args);
    }
}