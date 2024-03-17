package com.example.demo.exceptions;// import { HttpException, HttpStatus } from '@nestjs/common';

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ValidationException extends HttpClientErrorException {
  public ValidationException(String response) {
    super(HttpStatus.BAD_REQUEST, response);
  }
}

// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
// https://stackoverflow.com/questions/7561550/list-of-spring-runtime-exceptions
// UsernameNotFoundException("user with username " + username + " not found")
// HttpClientErrorException(HttpStatus.BAD_REQUEST, "Device with ID " + id + " not found")
// new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST)