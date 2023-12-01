package com.example.sourceSafeMaven.models;

public class FileNotReservedByUserException extends RuntimeException {
    public FileNotReservedByUserException(String message) {
        super(message);
    }
}