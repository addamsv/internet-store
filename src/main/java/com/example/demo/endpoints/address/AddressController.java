package com.example.demo.endpoints.address;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Address")
@RestController
@RequestMapping("api/v1/address")
@CrossOrigin("*")
public class AddressController {
    @GetMapping
    public ResponseEntity<String>get() {
        return new ResponseEntity<>("Address", HttpStatus.OK);
    }
}
