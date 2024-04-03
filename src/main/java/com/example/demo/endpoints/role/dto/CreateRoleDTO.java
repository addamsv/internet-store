package com.example.demo.endpoints.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateRoleDTO {
  @Schema(example = "ROLE_ADMIN", description = "Role Value")
  private String value;

  @Schema(
    example = "role for ADMIN",
    description = "description of role"
  )
  private String description;

  public CreateRoleDTO() {
  }

  public CreateRoleDTO(String value, String description) {
    this.value = value;
    this.description = description;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "CreateRoleDTO{" +
            "value='" + value + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
