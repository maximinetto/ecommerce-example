package com.maximinetto.example.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class ApiValidationError {
  private HttpStatus status;
  private LocalDateTime timestamp;
  private String message;

  private ApiValidationError() {
    timestamp = LocalDateTime.now();
  }

  public ApiValidationError(HttpStatus status) {
    this();
    this.status = status;
  }

  public ApiValidationError(HttpStatus status, Throwable ex) {
    this();
    this.status = status;
    this.message = "Unexpected error";
  }

  public ApiValidationError(HttpStatus status, String message, Throwable ex) {
    this();
    this.status = status;
    this.message = message;
  }
}
