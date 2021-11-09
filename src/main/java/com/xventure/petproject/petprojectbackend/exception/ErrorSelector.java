package com.xventure.petproject.petprojectbackend.exception;

import org.springframework.http.HttpStatus;

public enum ErrorSelector {
    EMAIL_SYNTAX_ERROR("Email address is incorrect",HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND("You are not registered in the system. Please register in the system",HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH("Password is incorrect. Please check and try again",HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorSelector(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
