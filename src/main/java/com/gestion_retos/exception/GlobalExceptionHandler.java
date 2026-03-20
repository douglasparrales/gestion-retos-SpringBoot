package com.gestion_retos.exception;

import com.gestion_retos.payload.ErrorResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<@NonNull ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){

        ErrorResponse error = new ErrorResponse(
                "Resource not found :(",
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<@NonNull ErrorResponse> handleIllegalState(IllegalStateException ex){
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    //Exception for @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(),error.getDefaultMessage());
        });

        ErrorResponse errorResponse = new ErrorResponse(
                "Validation failed",
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<@NonNull ErrorResponse> handleInvalidFormat(HttpMessageNotReadableException ex){
        ErrorResponse error = new ErrorResponse(
                "Invalid request format (check dates or JSON)",
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<@NonNull ErrorResponse> handleGeneral(Exception ex){
        ErrorResponse error = new ErrorResponse(
                "Internal server error :(",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDate.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}