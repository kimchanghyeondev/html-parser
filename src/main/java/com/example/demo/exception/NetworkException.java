package com.example.demo.exception;


import lombok.Getter;

@Getter
public class NetworkException extends RuntimeException {

    private final int statusCode;
    private final String body;

    public NetworkException(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public String getMessage() {
        return this.body;
    }
}
