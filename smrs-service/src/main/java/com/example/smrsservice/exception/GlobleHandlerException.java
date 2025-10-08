package com.example.smrsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;

@RestControllerAdvice
public class GlobleHandlerException {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException e, WebRequest request) {
              ErrorResponse errorResponse = ErrorResponse.builder()
                      .timestamp(new Date())
                      .status(HttpStatus.LOCKED.value())
                      .message(e.getMessage())
                      .path(request.getDescription(false).replace("uri=",""))
                      .build();
              return ResponseEntity.status(HttpStatus.LOCKED).body(errorResponse);


    }
}
