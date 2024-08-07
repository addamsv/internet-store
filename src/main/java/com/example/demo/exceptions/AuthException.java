package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class AuthException extends HttpClientErrorException {
    public AuthException(String response) {
        super(HttpStatus.FORBIDDEN, response);
    }
}