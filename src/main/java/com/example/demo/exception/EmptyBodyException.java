package com.example.demo.exception;


import lombok.Getter;

@Getter
public class EmptyBodyException extends RuntimeException {

    private final int statusCode;
    private final String body;

    public EmptyBodyException(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public String getMessage() {
        return this.body;
    }
}
