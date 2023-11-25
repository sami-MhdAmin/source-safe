package com.example.sourceSafeMaven;

import com.example.sourceSafeMaven.entities.Group;
//import com.example.sourceSafeMaven.entities.Role;
import com.example.sourceSafeMaven.entities.User;
//import com.example.sourceSafeMaven.repository.RoleRepository;
import com.example.sourceSafeMaven.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@SpringBootApplication
public class SourceSafeMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceSafeMavenApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(RoleRepository roleRepo) {
//        return (args) -> {
//            Role role=new Role();
//            role.setName("ROLE_ADMIN");
//            roleRepo.save(role);
//        };
//    }
}
