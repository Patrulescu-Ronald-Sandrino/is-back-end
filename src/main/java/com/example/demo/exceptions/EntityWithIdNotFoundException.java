package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class EntityWithIdNotFoundException extends ExceptionWithHttpStatus {
    public EntityWithIdNotFoundException(long id) {
        super(HttpStatus.NOT_FOUND, String.format("Entity with id %d not found", id));
    }
}
