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
public class AuthResponseModel {
    private String token;
    private Integer statusCode;
    private String userEmail;
    private String msg;

//    public AuthResponseModel(String token, Integer statusCode, String userEmail, String msg) {
//        this.token = token;
//        this.statusCode = statusCode;
//        this.userEmail = userEmail;
//        this.msg = msg;
//    }

//    private String errorMessage;  // Add this field

//    public AuthResponseModel(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
}
