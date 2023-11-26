package com.example.sourceSafeMaven.models;

import com.example.sourceSafeMaven.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private User user;
    private String userEmail;
    private String msg;
    private String errorMessage;  // Add this field

    public AuthenticationResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
