package com.example.smrsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleHandlerException {

  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException e, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(new Date())
        .status(HttpStatus.LOCKED.value())
        .message(e.getMessage())
        .path(request.getDescription(false).replace("uri=", ""))
        .build();
    return ResponseEntity.status(HttpStatus.LOCKED).body(errorResponse);
  }

  @ExceptionHandler(FileProcessingException.class)
  public ResponseEntity<ErrorResponse> handleFileProcessingException(FileProcessingException e, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(new Date())
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .path(request.getDescription(false).replace("uri=", ""))
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(InvalidFileFormatException.class)
  public ResponseEntity<ErrorResponse> handleInvalidFileFormatException(InvalidFileFormatException e,
      WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(new Date())
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.getMessage())
        .path(request.getDescription(false).replace("uri=", ""))
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<ErrorResponse> handleIOException(IOException e, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(new Date())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("File processing error: " + e.getMessage())
        .path(request.getDescription(false).replace("uri=", ""))
        .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e,
      WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(new Date())
        .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
        .message("File size exceeds maximum allowed size")
        .path(request.getDescription(false).replace("uri=", ""))
        .build();
    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e,
      WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    e.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    String message = "Validation failed: " + errors.toString();
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(new Date())
        .status(HttpStatus.BAD_REQUEST.value())
        .message(message)
        .path(request.getDescription(false).replace("uri=", ""))
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
