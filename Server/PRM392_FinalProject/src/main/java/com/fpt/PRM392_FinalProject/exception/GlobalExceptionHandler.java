package com.fpt.PRM392_FinalProject.exception;

import com.fpt.PRM392_FinalProject.model.CustomError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getError(), ex.getHttpStatus());
    }
}
