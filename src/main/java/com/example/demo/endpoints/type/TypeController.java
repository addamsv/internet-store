package com.example.demo.endpoints.type;

import com.example.demo.endpoints.DTO.Resp;
import com.example.demo.endpoints.DTO.RespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  @Operation(description = "Get All Types", summary = "Getting type")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  public ResponseEntity<List<Type>> getAll() {
    return this.typeService.getAll();
  }

  @PostMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "CREATE type", summary = "CREATE type")
  @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
  public ResponseEntity<RespDTO<Type>> create(@RequestBody Type dto) {
    return this.typeService.create(dto);
  }

  @PutMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "Update", summary = "Update Type")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  public ResponseEntity<String> update(@RequestBody Type type) {
    return typeService.update(type);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "Delete by ID", summary = "Delete by ID")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    return this.typeService.remove(id);
  }
}
