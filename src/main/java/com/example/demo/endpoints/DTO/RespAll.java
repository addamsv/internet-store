package com.example.demo.endpoints.DTO;

public class RespAll<T> {
    private String message;
    private String thePage;
    private String pages;
    private T data;

    public RespAll(String message, String thePage, String pages, T data) {
        this.message = message;
        this.thePage = thePage;
        this.pages = pages;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThePage() {
        return thePage;
    }

    public void setThePage(String thePage) {
        this.thePage = thePage;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
