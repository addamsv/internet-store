package com.example.demo.endpoints.basket;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.type.Type;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name= "Basket")
@RequestMapping("api/v1/basket")
@CrossOrigin("*")
public class BasketController {
    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }
    @GetMapping
    @Operation(description = "Get All Basket Devices", summary = "Get All Basket Devices")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<RespDTO<List<Basket>>> getAll() {
        return this.basketService.getAll();
    }

    @PostMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "ADD new device", summary = "ADD new device")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    public ResponseEntity<RespDTO<Type>> create(@RequestBody Basket dto) {
        return this.basketService.create(dto);
    }

    @PutMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Update", summary = "Update Device in Basket")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<String> update(@RequestBody Basket dto) {
        return this.basketService.update(dto);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Delete by ID", summary = "Delete by ID")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return this.basketService.delete(id);
    }
}
