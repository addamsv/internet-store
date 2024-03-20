package com.example.demo.endpoints.post;

import com.example.demo.endpoints.DTO.RespDTO;
import com.example.demo.endpoints.device.Device;
import com.example.demo.endpoints.device.dto.CreateDeviceDTO;
import com.example.demo.endpoints.post.dto.CreatePostDto;
import com.example.demo.endpoints.type.Type;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Tag(name= "Posts")
@RestController
@RequestMapping("api/v1/posts")
@CrossOrigin("*")
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  @Operation(description = "Get All Posts", summary = "GET All Posts")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  public ResponseEntity<RespDTO<List<Post>>> getAll() {
    return this.postService.getAll();
  }

  @PostMapping()
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "POST Create Post With Image", summary = "create",
  responses = {
    @ApiResponse(description = "Success", responseCode = "201", useReturnTypeSchema = true)
  })
  @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
  public ResponseEntity<Post> create(@ModelAttribute CreatePostDto dto) throws IOException {
    return this.postService.create(dto);
  }

  @PutMapping()
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "Update a Post", summary = "UPDATE a Post")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  public Post put(@RequestBody() CreatePostDto dto) {
    return this.postService.update(dto);
  }

  @DeleteMapping()
  @SecurityRequirement(name = "BearerAuth")
  @Operation(description = "Remove a Post", summary = "Remove a Post")
  @ApiResponse(responseCode = "200")
  public ResponseEntity<String> delete() {
    return this.postService.dell();
  }
}
