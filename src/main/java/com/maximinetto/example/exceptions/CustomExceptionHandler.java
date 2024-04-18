package com.maximinetto.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maximinetto.example.error.ApiValidationError;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleInvalidArgument(UserNotFoundException exception) {
   return buildResponseEntity(new ApiValidationError(HttpStatus.NOT_FOUND, exception.getMessage(), exception));
  }

  private ResponseEntity<Object> buildResponseEntity(ApiValidationError apiValidationError) {
    return new ResponseEntity<>(apiValidationError, apiValidationError.getStatus());
  }
}
