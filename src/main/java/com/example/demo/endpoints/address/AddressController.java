package com.example.demo.endpoints.address;

import com.example.demo.endpoints.DTO.RespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Address")
@RestController
@RequestMapping("api/v1/address")
@CrossOrigin("*")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<String>get() {
        return new ResponseEntity<>("Address", HttpStatus.OK);
    }

    @PostMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "CREATE New Address", summary = "CREATE an Address",
    responses = {
        @ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
    })
    public ResponseEntity<RespDTO<Address>> create(@RequestBody Address dto ) {
        return this.addressService.create(dto);
    }

    @PutMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Update endpoint", summary = "UPDATE Address",
    responses = {
        @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> update(@RequestBody Address dto) {
        return this.addressService.update(dto);
    }

    @DeleteMapping(path = "/{id}")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "DELETE Address", summary = "DELETE Address",
    responses = {
        @ApiResponse(description = "Success", responseCode = "200", useReturnTypeSchema = true)
    })
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return this.addressService.delete(id);
    }
}
