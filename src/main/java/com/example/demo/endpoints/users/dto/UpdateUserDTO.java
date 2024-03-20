package com.example.demo.endpoints.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateUserDTO {
    @Schema(example = "1", description = "Users ID")
    private Long id;

    @Schema(example = "a@a.a", description = "Users email")
    private String email;

    @Schema(example = "pass-1-asa", description = "User Password")
    private String password;

    @Schema(example = "ADMIN", description = "Users role")
    private String role = "ADMIN";

    public UpdateUserDTO(String email, String password, String role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "UpdateUserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
