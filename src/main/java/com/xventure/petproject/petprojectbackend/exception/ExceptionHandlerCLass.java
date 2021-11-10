package com.xventure.petproject.petprojectbackend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerCLass {

    @ExceptionHandler(value = {GenaricException.class})
    public ResponseEntity<Object> handleGenaricError(GenaricException e){
        return new ResponseEntity<>(new ExceptionModel(e.getMessage(),null,e.getHttpStatus(), ZonedDateTime.now()),e.getHttpStatus());
    }
}
