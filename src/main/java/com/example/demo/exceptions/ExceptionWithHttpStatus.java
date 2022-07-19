package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionWithHttpStatus extends RuntimeException {
    private final HttpStatus status;

    public ExceptionWithHttpStatus(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
