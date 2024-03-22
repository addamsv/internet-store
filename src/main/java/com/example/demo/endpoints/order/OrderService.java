package com.example.demo.endpoints.order;

import com.example.demo.endpoints.DTO.RespDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    public ResponseEntity<RespDTO<Order>> getById(Long id) {
        return new ResponseEntity<>(new RespDTO<>("GET_BY_ID Method has not implemented.", null), HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RespDTO<List<Order>>> get() {
        return null;
    }

    public ResponseEntity<RespDTO<Order>> create(Order dto) {
        return new ResponseEntity<>(new RespDTO<>("CREATE Method has not implemented.", null), HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> update(Order dto) {
        return ResponseEntity.ok("UPDATE Method has not implemented.");
    }

    public ResponseEntity<String> delete(Long id) {
        return ResponseEntity.ok("DELETE Method has not implemented.");
    }
}
