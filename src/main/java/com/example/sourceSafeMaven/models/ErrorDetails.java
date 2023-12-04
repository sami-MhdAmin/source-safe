package com.example.sourceSafeMaven.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private int errorcode;
    private String errorMessage;
    private String devErrorMessage;
    private Long timeStamp;
}

