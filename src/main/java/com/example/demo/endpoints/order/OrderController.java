package com.example.demo.endpoints.order;

import com.example.demo.endpoints.DTO.RespDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name= "Order")
@RequestMapping("api/v1/order")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Get Order By ID", summary = "GET Order By ID")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<RespDTO<Order>> getById(
        @PathVariable("id") Long id
    ) {
        return this.orderService.getById(id);
    }

    @GetMapping
    @Operation(description = "Get All Orders", summary = "GET All Orders")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<RespDTO<List<Order>>> get() {
        return this.orderService.get();
    }

    @PostMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "ADD new device", summary = "ADD order")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    public ResponseEntity<RespDTO<Order>> create(@RequestBody Order dto) {
        return this.orderService.create(dto);
    }

    @PutMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Update", summary = "UPDATE Order")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<String> update(
        @RequestBody Order dto
    ) {
        return this.orderService.update(dto);
    }

    @DeleteMapping(path = "/{id}")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Delete by ID", summary = "DELETE by ID")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<String> delete(
        @PathVariable("id") Long id
    ) {
        return this.orderService.delete(id);
    }
}
