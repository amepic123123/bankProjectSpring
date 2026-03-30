package com.example.demo.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;



@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
        ErrorResponse errorResponse = new ErrorResponse
        (HttpStatus.BAD_REQUEST.value(),
         ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse
        (HttpStatus.BAD_REQUEST.value(),
         ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse
        (HttpStatus.INTERNAL_SERVER_ERROR.value(),
         "An unexpected error occurred");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    
}
