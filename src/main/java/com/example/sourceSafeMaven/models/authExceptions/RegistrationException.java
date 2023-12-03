package com.example.sourceSafeMaven.models.authExceptions;

public class RegistrationException extends RuntimeException{
    public RegistrationException(String message) {
        super(message);
    }
}
