package com.example.sourceSafeMaven;

//import com.example.sourceSafeMaven.entities.enums.Role;
//import com.example.sourceSafeMaven.repository.RoleRepository;
import com.example.sourceSafeMaven.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.context.annotation.Configuration;
        import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
@EnableScheduling
public class SourceSafeMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceSafeMavenApplication.class, args);
    }

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncode;

    @Configuration
    public static class OpenApiConfig {

        // Swagger URL: http://localhost:8090/swagger-ui/index.html
        @Bean
        public OpenAPI customOpenAPI() {
            var securityKey = "Auth Token";
            var securityScheme = new SecurityScheme();
            securityScheme
                    .name("bearerAuth")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer");
            return new OpenAPI()
                    .components(
                            new Components().addSecuritySchemes(securityKey, securityScheme)
                    )
                    .addSecurityItem(new SecurityRequirement().addList(securityKey))
                    .info(
                            new Info()
                                    .title("SafeSource - API")
                                    .version("1.0.0")
                                    .description("Backend documentation for SafeSource")
                    );
        }
    }

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
