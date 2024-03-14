package com.example.demo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthRequest {
    @Schema(example = "sergy@a.a", description = "Users email")
    private String email;

    @Schema(example = "Sergey-aaa", description = "User Password")
    private String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
