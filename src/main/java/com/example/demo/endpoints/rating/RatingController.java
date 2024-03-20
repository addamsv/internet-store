package com.example.demo.endpoints.rating;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.post.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Rating")
@RestController
@RequestMapping("api/v1/rating")
@CrossOrigin("*")
public class RatingController {
    private final RatingService ratingService;
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    @Operation(description = "Get All Rating", summary = "GET All Rating")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseEntity<RespDTO<List<Rating>>> getAll() {
        return this.ratingService.getAll();
    }

    @PostMapping()
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Create Rating", summary = "CREATE Rating")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    public Rating create(@RequestBody() Post dto) throws IOException {
        return this.ratingService.create(dto);
    }

    @PutMapping()
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Update Rating", summary = "UPDATE Rating")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public Rating update(@RequestBody() Post dto) {
        return this.ratingService.update(dto);
    }

    @DeleteMapping()
    @SecurityRequirement(name = "BearerAuth")
    @Operation(description = "Remove Rating", summary = "REMOVE Rating")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> delete() {
        return this.ratingService.dell();
    }
}
