package com.example.demo.endpoints.DTO;

import org.springframework.http.HttpStatusCode;


public class Resp<T> {
    private final HttpStatusCode status;

    private final String message;

    private final T data;

    public Resp(String message, T data, HttpStatusCode status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Resp{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}