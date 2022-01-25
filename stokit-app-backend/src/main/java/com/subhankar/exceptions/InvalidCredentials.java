package com.subhankar.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentials extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public InvalidCredentials(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
