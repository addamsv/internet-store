package com.example.demo.endpoints.basket;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.order.Order;
import com.example.demo.endpoints.type.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    public ResponseEntity<RespDTO<List<Basket>>> getAll() {
        return null;
    }

    public ResponseEntity<RespDTO<Type>> create(Basket dto) {
        return null;
    }

    public ResponseEntity<String> update(Basket dto) {
        return ResponseEntity.ok("UPDATE Method has not implemented.");
    }

    public ResponseEntity<String> delete(Long id) {
        return ResponseEntity.ok("DELETE Method has not implemented.");
    }
}
