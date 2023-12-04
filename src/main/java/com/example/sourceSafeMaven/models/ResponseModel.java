package com.example.sourceSafeMaven.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel<T> {
    private Integer statuesCode;
    private String msg;
    private T responseObject;
}
