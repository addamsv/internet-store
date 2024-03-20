package com.example.demo.endpoints.rating;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.post.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    public ResponseEntity<RespDTO<List<Rating>>> getAll() {
        return null;
    }

    public Rating create(Post dto) {
        return null;
    }

    public Rating update(Post dto) {
        return null;
    }

    public ResponseEntity<String> dell() {
        return null;
    }
}
