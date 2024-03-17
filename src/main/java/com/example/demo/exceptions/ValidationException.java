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

//export const E_USER_EMAIL_TAKEN = "This email is already taken!";
//export const E_USER_NOT_FOUND = "User not found!";
//export const E_USER_ALREADY_EXISTS = "User already exists!";
//export const E_INCORRECT_EMAIL_OR_PASSWORD = "Email or password entered is incorrect!";
//export const E_PASSWORD_INCORRECT = "The password entered is incorrect!";
//export const E_TOO_MANY_REQUESTS = "Too many requests!";
//export const E_TRANSACTION_NOT_FOUND = "User not found! Please make sure the transaction ID entered is valid!";
//export const E_UNAUTHORIZED_ACCESS_TO_RESOURCE = "You are now allowed to access this resource!";