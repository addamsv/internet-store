package com.example.demo.endpoints.auth;

import com.example.demo.endpoints.users.Users;
import com.example.demo.endpoints.users.UsersRepository;
import com.example.demo.endpoints.users.UsersService;
import com.example.demo.guard.jwt.JwtService;
import com.example.demo.guard.roles.RolesGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
  private final UsersRepository usersRepository;
  private final JwtService jwtService;
  private final UsersService usersService;
  @Autowired
  public AuthService (
          UsersRepository usersRepository,
          UsersService usersService,
          JwtService jwtService,
          RolesGuard rolesGuard
  ) {
    this.usersRepository = usersRepository;
    this.jwtService = jwtService;
    this.usersService = usersService;
  }

  public AuthResponse login(AuthRequest req) {
    Users user = this.validateUser(req);
    Map<String, Object> clime = new HashMap<>();
    clime.put("role", user.getRole());
    String jwtToken = jwtService.generateToken(clime, user);
    return new AuthResponse(jwtToken);
  }

  public AuthResponse registration(AuthRequest req) {
    Optional<Users> userCandidate = usersRepository.findUserByEmail(req.getEmail());

    if (userCandidate.isPresent()) {
      throw new IllegalStateException("Login/email is already taken (The USER is already exist)");
    }

    String passHash = jwtService.getPassHash(req.getPassword());

    Users userEntity = new Users(passHash, req.getEmail());

    Users user = this.usersService.addUser(userEntity);

    Map<String, Object> clime = new HashMap<>();
    clime.put("role", user.getRole());

    String jwtToken = jwtService.generateToken(clime, user);
    return new AuthResponse(jwtToken);
  }

  private Users validateUser(AuthRequest req) {
    Users userCandidate = this.usersRepository.findUserByEmail(req.getEmail())
            .orElseThrow(() -> new IllegalStateException("User does not exist"));

    if (req.getPassword() != null && req.getPassword().isEmpty()) {
      throw new IllegalStateException("password should not be empty");
    }

    if (!jwtService.isPasswordEqual(req.getPassword(), userCandidate.getPassword())) {
      throw new IllegalStateException("password should not be empty");
    }
// !Objects.equals(userCandidate.getPassword(), req.getPassword())

    return userCandidate;

  }
}
