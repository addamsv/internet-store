package com.example.demo.endpoints.type;

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


  @DeleteMapping("/{typeId}")
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "Delete",
          summary = "Remove Type"
  )
  @ApiResponse(
          responseCode = "200",
          useReturnTypeSchema = true
  )
  public void delete(@PathVariable("typeId") Long id) {
    this.typeService.remove(id);
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
  public void update(@RequestBody Type type) {
    typeService.update(type);
  }
}
