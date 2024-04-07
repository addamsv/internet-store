package com.example.demo.endpoints.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Role")
@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping("api/v1/role")
@CrossOrigin("*")
public class RoleController {
  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping()
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Successfully created"),
    @ApiResponse(responseCode = "404", description = "Not found - The entity was not found")
  })
  @Operation(description = "Create ROLE", summary = "Returns Created Role")
  public ResponseEntity<Role> create(@RequestBody() Role dto) {
    return roleService.create(dto);
  }

  @GetMapping(path = "/{value}")
  @Operation(description = "GET Role By Value in PATH", summary = "Get Role",
  responses = {
    @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true),
    @ApiResponse(description = "Forbidden", responseCode = "403", useReturnTypeSchema = false)
  })
  public ResponseEntity<Role> getByValue(@PathVariable("value") String value) {
    return roleService.getRoleByValue(value);
  }

  @GetMapping(params = "usersId")
  @Operation(description = "GET Role By Value in PATH", summary = "Get Role",
  responses = {
    @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true),
    @ApiResponse(description = "Forbidden", responseCode = "403", useReturnTypeSchema = false)
  })
  public ResponseEntity<List<Role>> getByUserId(@RequestParam(value = "usersId") Long userId) {
    return roleService.getRoleByUserId(userId);
  }

  @GetMapping
  @Operation(description = "GET All", summary = "GET All",
  responses = {
    @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true),
  })
  public ResponseEntity<List<Role>> get() {
    return roleService.getAll();
  }

  @PutMapping()
  @Operation(description = "Update endpoint", summary = "Update a Role",
  responses = {
      @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
  })
  public ResponseEntity<String> update(@RequestBody() Role dto) {
    return roleService.update(dto);
  }

  @DeleteMapping(path = "/{path}")
//  @Operation(
//    description = "DELETE",
//    summary = "Remove a Role",
//    responses = {
//      @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
//    }
//  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Ok"
//              content = {
//                @Content(
//                  mediaType = "application/json",
//                  schema = @Schema(implementation = CreateRoleDTO.class)//implementation = Role.class
//                )
//              }
      ),
      @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "404", description = "Customer not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(example = "Internal server error")
              )
          }
      )
  })
  public ResponseEntity<String> delete(@PathVariable("path") Long id) {
    return roleService.delete(id);
  }
}
