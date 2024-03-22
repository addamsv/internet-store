package com.example.demo.endpoints.DTO;

public class RespAll<T> {
    private final String message;
    private final String thePage;
    private final String pages;
    private final T data;

    public RespAll(String message, String thePage, String pages, T data) {
        this.message = message;
        this.thePage = thePage;
        this.pages = pages;
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespAll{" +
                "message='" + message + '\'' +
                ", thePage='" + thePage + '\'' +
                ", pages='" + pages + '\'' +
                ", data=" + data +
                '}';
    }
}
