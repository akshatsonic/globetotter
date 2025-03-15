package com.akshatsonic.globetotter.controller;

import com.akshatsonic.globetotter.dto.GenericResponseEntity;
import com.akshatsonic.globetotter.exceptions.InvalidPasswordException;
import com.akshatsonic.globetotter.exceptions.UserAlreadyPresentException;
import com.akshatsonic.globetotter.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class AuthControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { UserNotFoundException.class, UserAlreadyPresentException.class, InvalidPasswordException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        GenericResponseEntity responseEntity = GenericResponseEntity.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseEntity);
    }
}
