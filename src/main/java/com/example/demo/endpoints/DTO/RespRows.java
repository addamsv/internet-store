package com.example.demo.endpoints.DTO;

public record RespRows<T>(String message, Long count, T rows) {

    @Override
    public String toString() {
        return "RespRows{" +
                "message='" + message + '\'' +
                ", count='" + count + '\'' +
                ", rows=" + rows +
                '}';
    }
}
