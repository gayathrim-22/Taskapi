package com.jspiders.taskapi.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> arithmeticExceptionHandler(ArithmeticException ex)
    {
        log.error("Handling ArithmeticException ",ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex)
    {
        System.out.println("Handling NullPointerException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex)
    {
        System.out.println("Handling illegalArgumentException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<String> invalidNameExceptionHandler(InvalidNameException ex)
    {
        System.out.println("Handling illegalArgumentException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>
    methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {
//        FieldError fieldError = ex.getFieldError();
//
//        String field        = fieldError.getField();
//        String errorMessage = fieldError.getDefaultMessage();
//
//        Map<String,String> errorMap = new HashMap<>();
//        errorMap.put(field,errorMessage);

        Map<String,String> errorMap = new HashMap<>();

        List<FieldError> fieldErrors = ex.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {

            String field = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();

            errorMap.put(field, errorMessage);
        }

        log.error("Validation Error : {}",errorMap);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> handleDuplicateUserException(DuplicateUserException ex)
    {
        log.error("Duplicate user ",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex)
    {
        log.error("Data not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DATA NOT FOUND");
    }

}