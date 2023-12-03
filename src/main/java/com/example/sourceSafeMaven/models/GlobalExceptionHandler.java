package com.example.sourceSafeMaven.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppApiException.class)
    public ResponseEntity<ErrorDetails> handleReservationApiException(
            AppApiException excption,
            WebRequest request
    ){
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorcode(excption.getStatus().value());
        errorDetails.setErrorMessage(excption.getLocalizedMessage());
        errorDetails.setDevErrorMessage(request.getDescription(false));
        errorDetails.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(
            AccessDeniedException excption,
            WebRequest request
    ){
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(excption.getLocalizedMessage());
        errorDetails.setDevErrorMessage(request.getDescription(false));
        errorDetails.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception excption,
            WebRequest request
    ){
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(excption.getLocalizedMessage());
        errorDetails.setDevErrorMessage(request.getDescription(false));
        errorDetails.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
