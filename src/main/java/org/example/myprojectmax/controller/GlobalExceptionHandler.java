package org.example.myprojectmax.controller;

import jakarta.validation.ConstraintViolationException;
import org.example.myprojectmax.service.AlreadyExistException;
import org.example.myprojectmax.service.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> handlerAlreadyExistException(AlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException e) {

        StringBuilder responseMessage = new StringBuilder();

        e.getConstraintViolations().forEach(constraintViolation -> {
            String message = constraintViolation.getMessage();
            responseMessage.append(message).append("\n");
        });

        return new ResponseEntity<>(responseMessage.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
