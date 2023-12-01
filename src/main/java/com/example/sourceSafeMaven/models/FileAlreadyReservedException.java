package com.example.sourceSafeMaven.models;

public class FileAlreadyReservedException extends RuntimeException {
    public FileAlreadyReservedException(String message) {
        super(message);
    }
}