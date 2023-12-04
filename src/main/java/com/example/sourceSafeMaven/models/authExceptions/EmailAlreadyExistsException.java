package com.example.sourceSafeMaven.models.authExceptions;

public class EmailAlreadyExistsException extends RegistrationException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}