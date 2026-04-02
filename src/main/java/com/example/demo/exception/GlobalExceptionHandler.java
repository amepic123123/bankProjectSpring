package com.example.demo.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.ErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;



@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex){
        ErrorResponse errorResponse = new ErrorResponse
        (HttpStatus.BAD_REQUEST.value(),
         ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse
        (HttpStatus.BAD_REQUEST.value(),
         errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex){
        ErrorResponse errorResponse = new ErrorResponse
        (HttpStatus.BAD_REQUEST.value(),
         ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

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
        (HttpStatus.INTERNAL_SERVER_ERROR.value(),
         ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse
        (HttpStatus.INTERNAL_SERVER_ERROR.value(),
         "An unexpected error occurred");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    
}
