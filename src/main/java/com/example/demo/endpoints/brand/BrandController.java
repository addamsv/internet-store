package com.example.demo.endpoints.brand;

import com.example.demo.endpoints.DTO.RespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Brand")
@RestController
@RequestMapping("api/v1/brand")
@CrossOrigin("*")
public class BrandController {
  private final BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping(params = "name")
  @Operation(
      description = "Get Brand By Name",
      summary = "Get Brand",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              useReturnTypeSchema = true
          )
      }
  )
  public ResponseEntity<RespDTO<Brand>> getByName(@RequestParam(value = "name", required = false) String name) {
    return this.brandService.getByName(name);
  }

  @GetMapping
  @Operation(description = "Get All Brands", summary = "Get all Brands",
  responses = {
    @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
  })
//  public ResponseEntity<RespDTO<List<Brand>>> getAll() {
  public ResponseEntity<List<Brand>> getAll() {
    return this.brandService.getAll();
  }

  @PostMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "CREATE New Brand", summary = "CreateRoleDTO Brand",
  responses = {
    @ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
  })
  public ResponseEntity<RespDTO<Brand>> create(@RequestBody Brand dto ) {
    return this.brandService.create(dto);
  }

  @PutMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "Update endpoint", summary = "Update Brand",
  responses = {
      @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
      @ApiResponse(responseCode = "403", description = "Forbidden")
  })
  public ResponseEntity<String> update(@RequestBody Brand dto) {
      return this.brandService.update(dto);
  }

  @DeleteMapping(path = "/{brandId}")
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "DELETE Brand", summary = "Remove Brand",
  responses = {
    @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
  })
  public ResponseEntity<String> delete(@PathVariable("brandId") Long id) {
    return this.brandService.delete(id);
  }
}
