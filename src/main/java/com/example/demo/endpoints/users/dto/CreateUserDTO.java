package com.example.demo.endpoints.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateUserDTO {
    @Schema(example = "pass-1-asa", description = "User Password")
    private String password;

    @Schema(example = "a@a.a", description = "Users email")
    private String email;

    @Schema(example = "ADMIN", description = "Users role")
    private String role = "ADMIN";

    public CreateUserDTO(String email, String password, String role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CreateUserDTO{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
