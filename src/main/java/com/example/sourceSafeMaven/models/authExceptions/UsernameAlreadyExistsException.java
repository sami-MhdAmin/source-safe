package com.example.sourceSafeMaven.models.authExceptions;

import com.example.sourceSafeMaven.models.authExceptions.RegistrationException;

public class UsernameAlreadyExistsException extends RegistrationException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
