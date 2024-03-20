package com.example.demo.endpoints.DTO;

import org.springframework.lang.Nullable;

public class RespDTO<T> {
    private String message;

    private T data;

    public RespDTO(String message, @Nullable T data) {
        this.message = message;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

//    @Override
//    public String toString() {
//        return "RespDTO{" +
//                (message != null ? "message='" + message + '\'' : "") +
//                (message != null && data != null ? ", " : "") +
//                (data != null ? "data=" + data : "") +
//                '}';
//    }
}
