package com.example.universitymanagement.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxAttendeesExceededException extends RuntimeException {
    public MaxAttendeesExceededException(String message) {
        super(message);
    }
}
