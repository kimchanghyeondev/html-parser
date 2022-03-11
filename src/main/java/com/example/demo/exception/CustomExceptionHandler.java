package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {NetworkException.class})
    protected ResponseEntity<String> handleNetworkException(NetworkException e) {
        int statusCode = e.getStatusCode();
        String message = e.getMessage();
        return ResponseEntity.status(statusCode).body(message);
    }

    @ExceptionHandler(value = {EmptyBodyException.class})
    protected ResponseEntity<String> handleEmptyBodyException(EmptyBodyException e) {
        int statusCode = e.getStatusCode();
        String message = e.getMessage();
        return ResponseEntity.status(statusCode).body(message);
    }
}
