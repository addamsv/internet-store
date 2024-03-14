package com.example.demo.auth;

import com.example.demo.users.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {
  private final AuthService authService;
  public AuthController (AuthService authService) { this.authService = authService; }

  @PostMapping("/login")
  @ApiResponse(
          responseCode = "201",
          useReturnTypeSchema = true
  )
  @Operation(
          description = "Post",
          summary = "Login"
  )
  public AuthResponse login(@RequestBody AuthRequest req) {
    return this.authService.login(req);
  }

  @PostMapping("/registration")
  @ApiResponse(
          responseCode = "201",
          useReturnTypeSchema = true
  )
  @Operation(
          description = "Post",
          summary = "Registration"
  )
  public AuthResponse registration(@RequestBody AuthRequest req) {
    return this.authService.registration(req);
  }
}
