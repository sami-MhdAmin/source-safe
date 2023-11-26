package com.example.sourceSafeMaven.security.auth;

import com.example.sourceSafeMaven.entities.Role;
import com.example.sourceSafeMaven.entities.User;
import com.example.sourceSafeMaven.models.AuthenticationResponse;
import com.example.sourceSafeMaven.repository.UserRepository;
import com.example.sourceSafeMaven.security.JwtService;
import com.example.sourceSafeMaven.security.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
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

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder() // it return token with response
                .token(jwtToken)
//                .user(user)
                .userEmail(user.getEmail())
                .msg("User added successfully")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
        try {
            System.out.println("i am in try");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .userEmail(user.getEmail())
                    .msg("User added successfully")
                    .build();
        } catch (UserNotFoundException e){
            System.out.println("i am in catch");

            return AuthenticationResponse.builder()
                    .errorMessage("User not found")
                    .build();
        }

    }
}
