package com.example.demo.endpoints.address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address")
@RestController
@RequestMapping("api/v1/address")
@CrossOrigin("*")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>>get() {
        return this.addressService.get();
    }

    @PostMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "CREATE New Address", summary = "CREATE an Address",
    responses = {
        @ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
    })
    public ResponseEntity<Address> create(@RequestBody Address dto ) {
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
