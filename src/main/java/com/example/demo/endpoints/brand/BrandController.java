package com.example.demo.endpoints.brand;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping
  @Operation(
          description = "Get All Brands",
          summary = "Get all Brands",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          useReturnTypeSchema = true
                  )
          }
  )
  public List<Brand> getAll() {
    return this.brandService.getAll();
  }

  @PostMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "Post endpoint",
          summary = "Create Brand",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "201",
                          useReturnTypeSchema = true
                  )
          }
  )
  public void create(@RequestBody Brand dto ) {
    this.brandService.create(dto);
  }

  @PutMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
      description = "Update endpoint", summary = "Update Brand",
      responses = {
          @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
          @ApiResponse(description = "Forbidden", responseCode = "403")
      }
  )
  public void update(@RequestBody Brand dto) {
      this.brandService.update(dto);
  }

  @DeleteMapping(path = "/{brandId}")
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "Delete endpoint",
          summary = "Remove Brand",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          useReturnTypeSchema = true
                  )
          }
  )
  public void delete(@PathVariable("brandId") Long id) {
    this.brandService.remove(id);
  }
}
