package com.example.demo.endpoints.post;

import com.example.demo.endpoints.post.dto.CreatePostDto;
import com.example.demo.endpoints.post.dto.UpdatePostDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name= "Post")
@RestController
@RequestMapping("api/v1/post")
@CrossOrigin("*")
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping()
  @Operation(description = "Get All Posts", summary = "GET All Posts")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  public ResponseEntity<List<Post>> getAll() {
    return this.postService.getAll();
  }

  @PostMapping()
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "POST Create Post With Image", summary = "create",
  responses = {
    @ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
  })
  @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
  public ResponseEntity<Post> create(@RequestBody CreatePostDto dto) throws IOException {
    return this.postService.create(dto);
  }

  @PutMapping()
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "Update a Post", summary = "UPDATE a Post")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  public ResponseEntity<String> update(@RequestBody() UpdatePostDTO dto) throws IOException {
    return this.postService.update(dto);
  }

  @DeleteMapping(path = "/{id}")
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "Remove a Post", summary = "Remove a Post")
  @ApiResponse(responseCode = "200")
  public ResponseEntity<String> delete(@PathVariable("id") Long id) throws IOException {
    return this.postService.delete(id);
  }
}
