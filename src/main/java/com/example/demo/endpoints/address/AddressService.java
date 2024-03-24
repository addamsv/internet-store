package com.example.demo.endpoints.address;

import com.example.demo.endpoints.DTO.RespDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    public ResponseEntity<RespDTO<List<Address>>> getAll() {
        return null;
    }

    public ResponseEntity<RespDTO<Address>> create(Address dto) {
        return null;
    }

    public ResponseEntity<String> update(Address dto) {
        return ResponseEntity.ok("UPDATE Method has not implemented.");
    }

    public ResponseEntity<String> delete(Long id) {
        return ResponseEntity.ok("DELETE Method has not implemented.");
    }
}
