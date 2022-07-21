package com.example.demo.controller;

import com.example.demo.exceptions.ExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @Value("${custom.debug}")
    private boolean debug = true;

    @ExceptionHandler(ExceptionWithHttpStatus.class)
    public ResponseEntity<Object> handleExceptionWithHttpStatus(ExceptionWithHttpStatus e, WebRequest request) {
        // IDEA 1: use body with timestamp and message (see https://zetcode.com/springboot/controlleradvice/)
        //  IDEA: use {"timestamp":"2022-07-14T05:50:55.266+00:00","status":404,"error":"Not Found","path":"/abc"}
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
//        return wrapper(e, e.getStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleThrowable(Throwable e, WebRequest request) {
//        return ResponseEntity.internalServerError().body(e.getMessage());
        return wrapper(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Object> wrapper(Throwable e, HttpStatus status) {
        // TODO MAYBE: replace this with a proper logger
        System.err.println("debug = " + debug);
        if (debug) {
            System.err.println("e.getMessage() = " + e.getMessage());
            System.err.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            e.printStackTrace();
        }
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
