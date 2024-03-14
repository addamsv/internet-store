package com.example.demo.brand;//import { Body, Controller, Delete, Get, Post, Put } from '@nestjs/common';

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
//  constructor(private brandService: BrandService) {}
  private final BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping
  @Operation(
          description = "Get endpoint",
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
    return this.brandService.getAllBrands();
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
  public void createPost(@RequestBody Brand dto ) {
    this.brandService.create(dto);
  }

  @PutMapping
  @SecurityRequirement(name = "BearerAuth")
  @Operation(
          description = "Put endpoint",
          summary = "Update Brand",
          responses = {
                  @ApiResponse(
                          description = "Success",
                          responseCode = "200",
                          useReturnTypeSchema = true
                  )
          }
  )
  public Brand updateBrand(@RequestBody Brand dto) {
    return this.brandService.updateBrand(dto);
  }

  @DeleteMapping
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
  public void deleteBrand(@PathVariable("brandId") Long id) {
    this.brandService.removeBrand(id);
  }
}
