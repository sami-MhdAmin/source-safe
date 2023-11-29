package com.example.sourceSafeMaven;

//import com.example.sourceSafeMaven.entities.Role;
//import com.example.sourceSafeMaven.repository.RoleRepository;
import com.example.sourceSafeMaven.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SourceSafeMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceSafeMavenApplication.class, args);
    }

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncode;
/*
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, GroupRepository groupRepository, FileRepository fileRepository) {
        return args -> {
            int numUsers = 5;
            int numGroups = 5;

            for (int i = 1; i <= numUsers; i++) {
                User user = new User();
                user.setUserName("name" + i);
                user.setPassword("123");
                user.setEmail("name" + i + "@gmail.com");
                var user2 =User.builder()
                        .userName(user.getUsername())
                        .email(user.getEmail())
                        .password(passwordEncode.encode(user.getPassword()))
                        .role(Role.USER)
                        .build();
                userRepository.save(user2);
                var jwtToken = jwtService.generateToken(user);
                AuthenticationResponse.builder()
                        .token(jwtToken)
//                .user(user)
                        .userEmail(user.getEmail())
                        .msg("User added successfully")
                        .build();

            }

            for (int i = 1; i <= numGroups; i++) {
                Group group = new Group();
                groupRepository.save(group);
            }
        };
    }
*/}
