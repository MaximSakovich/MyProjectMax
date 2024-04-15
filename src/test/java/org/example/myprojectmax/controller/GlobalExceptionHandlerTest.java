package org.example.myprojectmax.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.example.myprojectmax.service.AlreadyExistException;
import org.example.myprojectmax.service.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handlerNullPointerException() {
        ResponseEntity<String> responseEntity = globalExceptionHandler.handlerNullPointerException(new NullPointerException());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void handlerNotFoundException() {
        ResponseEntity<String> responseEntity = globalExceptionHandler.handlerNotFoundException(new NotFoundException("Resource not found"));
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void handlerAlreadyExistException() {
        ResponseEntity<String> responseEntity = globalExceptionHandler.handlerAlreadyExistException(new AlreadyExistException("Resource not found"));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void handlerConstraintViolationException() {
        // Создаем фиктивный набор ConstraintViolation
        Set<ConstraintViolation<?>> violations = new HashSet<>();
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("Constraint violation message");
        violations.add(violation);

        // Создаем объект ConstraintViolationException с фиктивным набором нарушений
        ConstraintViolationException exception = new ConstraintViolationException("Constraint violation message", violations);

        ResponseEntity<String> responseEntity = globalExceptionHandler.handlerConstraintViolationException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void handlerSQLIntegrityConstraintViolationException() {
        ResponseEntity<String> responseEntity = globalExceptionHandler.handlerSQLIntegrityConstraintViolationException(new SQLIntegrityConstraintViolationException());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}