package com.rabin.redispractiseproject.advice;

import com.rabin.redispractiseproject.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.InternalError;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandling {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfUserExist(UserAlreadyExistException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.ALREADY_REPORTED.toString());
        return ResponseEntity.ok(errorMap);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfUserNotFound(UserNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(InvalidCustomerInfoException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfInvalidCustomerInfo(InvalidCustomerInfoException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfInvalidEmail(InvalidEmailException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfInvalidPhoneNumber(InvalidPhoneNumberException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfInvalidPassword(InvalidPasswordException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfInternalError(InternalError ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return ResponseEntity.ok(errorMap);
    }
}
