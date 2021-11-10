package com.xventure.petproject.petprojectbackend.exception;

import org.springframework.http.HttpStatus;

public class GenaricException extends RuntimeException{
    private HttpStatus httpStatus;

    public GenaricException(ErrorSelector errorSelector) {
        super(errorSelector.getMessage());
        this.httpStatus = errorSelector.getHttpStatus();
    }

    public GenaricException(ErrorSelector errorSelector, Throwable cause) {
        super(errorSelector.getMessage(), cause);
        this.httpStatus = errorSelector.getHttpStatus();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
