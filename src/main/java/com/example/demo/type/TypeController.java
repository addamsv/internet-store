package com.example.demo.type;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Type")
@RestController
@RequestMapping("api/v1/type")
@CrossOrigin("*")
public class TypeController {
  private final TypeService typeService;

  @Autowired
  public TypeController(TypeService typeService) {
    this.typeService = typeService;
  }

  @GetMapping
  @Operation(
          description = "Get",
          summary = "Getting type"
  )
  @ApiResponse(
          responseCode = "200",
          useReturnTypeSchema = true
  )
  public List<Type> getAll() {
    return this.typeService.getAll();
  }

  @PostMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "Post",
          summary = "type creation"
  )
  @ApiResponse(
          responseCode = "201",
          useReturnTypeSchema = true
  )
  public Type create(@RequestBody Type type) {
    return this.typeService.create(type);
  }


  @DeleteMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "Delete",
          summary = "Remove Type"
  )
  @ApiResponse(
          responseCode = "200",
          useReturnTypeSchema = true
  )
  public void deleteType(@RequestBody Type type) {
    this.typeService.removeType(type.getId());
  }

  @PutMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "Update",
          summary = "Update Type"
  )
  @ApiResponse(
          responseCode = "200",
          useReturnTypeSchema = true
  )
  public Type updateType(@RequestBody Type type) {
    return typeService.updateType(type);
  }
}
