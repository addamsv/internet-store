package com.example.demo.endpoints.auth;

import com.example.demo.utils.PrintEx;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController (AuthService authService) {
    this.authService = authService;
  }

  /**
   * GET activate user by link
   *
   * @param link String (PathVariable)
   * @return AuthResponse
   */
  @GetMapping("/activate/{link}")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  @Operation(description = "GET activate user", summary = "GET activate user by LINK")
  public ResponseEntity<String> activate(@PathVariable("link") String link) {
    return this.authService.activate(link);
  }

  /**
   * GET logout
   *
   * @param refreshToken String (from Cookie)
   * @return AuthResponse
   */
  @GetMapping("/sign-out")
  @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
  @Operation(description = "GET  Logout", summary = "GET Logout")
  public ResponseEntity<String> logout(@CookieValue("refresh-token") String refreshToken, HttpServletResponse response) {

    PrintEx.printTitle("refreshToken logout");
    System.out.println(refreshToken);

    this.authService.remRefreshToken(response);

    return this.authService.logout(refreshToken);
  }




  /**
   * POST refresh token
   *
   * @param refreshToken String (from Cookie)
   * @return AuthResponse
   */
  @PostMapping("/refresh")
  @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
  @Operation(description = "REFRESH TOKEN", summary = "REFRESH TOKEN")
  public AuthResponse refresh(@CookieValue("refresh-token") String refreshToken, HttpServletResponse response) {

    PrintEx.printTitle("old refreshToken");
    System.out.println(refreshToken);

    AuthResponse authResponse = this.authService.refresh(refreshToken);

    this.authService.setRefreshToken(authResponse.getRefreshToken(), response);

    PrintEx.printTitle("new refreshToken");
    System.out.println(authResponse.getRefreshToken());

    return authResponse;
  }

  /**
   * POST login
   *
   * @param request AuthRequest (RequestBody)
   * @return AuthResponse
   */
  @PostMapping("/login")
  @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
  @Operation(description = "Post", summary = "Login")
  public AuthResponse login(@RequestBody AuthRequest request, HttpServletResponse response) {
    AuthResponse authResponse = this.authService.login(request);

    this.authService.setRefreshToken(authResponse.getRefreshToken(), response);

    PrintEx.printTitle("refreshToken");
    System.out.println(authResponse.getRefreshToken());

    return authResponse;
  }


  /**
   * POST registration
   *
   * @param request AuthRequest (RequestBody)
   * @return AuthResponse
   */
  @PostMapping("/registration")
  @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
  @Operation(description = "Post", summary = "Registration")
  public AuthResponse registration(@RequestBody AuthRequest request, HttpServletResponse response) {
    AuthResponse authResponse = this.authService.registration(request);

    this.authService.setRefreshToken(authResponse.getRefreshToken(), response);

    PrintEx.printTitle("registration refreshToken");
    System.out.println(authResponse.getRefreshToken());

    return authResponse;
  }
}
