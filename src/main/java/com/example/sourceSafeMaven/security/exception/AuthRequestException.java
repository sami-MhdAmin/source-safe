package com.example.sourceSafeMaven.security.exception;

public class AuthRequestException extends RuntimeException{
    public AuthRequestException(String message) {
        super(message);
    }

    public AuthRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
