package com.example.sourceSafeMaven.security.auth;

import com.example.sourceSafeMaven.entities.enums.Role;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.models.AuthResponseModel;
import com.example.sourceSafeMaven.models.authExceptions.EmailAlreadyExistsException;
import com.example.sourceSafeMaven.models.authExceptions.RegistrationException;
import com.example.sourceSafeMaven.models.authExceptions.UsernameAlreadyExistsException;
import com.example.sourceSafeMaven.repository.UserRepository;
import com.example.sourceSafeMaven.security.JwtService;
import com.example.sourceSafeMaven.security.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseModel register(RegisterRequest request)throws RegistrationException {
        // Business logic for registration
        if (usernameAlreadyExists(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        if (emailAlreadyExists(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // Perform registration
        var user = User.builder()
                .userName(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponseModel.builder() // it return token with response
                .token(jwtToken)
//                .user(user)
                .userEmail(user.getEmail())
                .msg("User added successfully")
                .build();
    }

    private boolean usernameAlreadyExists(String username) {
        // Check if username already exists in the database
        boolean exists = userRepository.existsByUserName(username);
        // Implementation details...
        return exists;
    }

    private boolean emailAlreadyExists(String email) {
        // Check if email already exists in the database
        boolean exists = userRepository.existsByEmail(email);
        // Implementation details...
        return exists;
    }

    public AuthResponseModel authenticate(AuthenticationRequest request) throws BadCredentialsException {
//        try {
            System.out.println("i am in try");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            System.out.println("i am in try2");

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            System.out.println("i am in try3");

            var jwtToken = jwtService.generateToken(user);
        final AuthResponseModel authResponseModel;
            return
                    AuthResponseModel.builder()
                    .statusCode(HttpStatus.OK.value())
                    .token(jwtToken)
                    .userEmail(user.getEmail())
                    .msg("User added successfully")
                    .build();
//        authResponseModel = new AuthResponseModel(
//                jwtToken,
//                HttpStatus.OK.value(),
//                user.getEmail(),
//                "Successfully logged in"
//        );
//        } catch (BadCredentialsException e){
//            System.out.println("i am in catch");
//            return AuthenticationResponse.builder()
//                    .errorMessage("User not found")
//                    .build();
//        }
    }
}
