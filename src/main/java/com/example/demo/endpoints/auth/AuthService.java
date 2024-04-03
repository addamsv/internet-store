package com.example.demo.endpoints.auth;

import com.example.demo.endpoints.role.Role;
import com.example.demo.endpoints.role.RoleRepository;
import com.example.demo.endpoints.users.Users;
import com.example.demo.endpoints.users.UsersRepository;
import com.example.demo.endpoints.users.UsersService;
import com.example.demo.endpoints.users.dto.CreateUserDTO;
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
    //   id: 8,
    //   email: "admin2@a.a",
    //   roles: [
    //     {
    //       id: 1,
    //       value: "ADMIN",
    //       description: "Administrator",
    //       createdAt: "2023-06-29T12:12:53.132Z",
    //       updatedAt: "2023-06-29T12:12:53.132Z",
    //     },
    //   ],

    Map<String, Object> clime = new HashMap<>();
    clime.put("id", user.getId());
    clime.put("email", user.getEmail());
    clime.put("roles", user.getRoles());

    String jwtToken = jwtService.generateToken(clime, user);
    return new AuthResponse(jwtToken);
  }

  public AuthResponse registration(AuthRequest req) {
    Optional<Users> userCandidate = usersRepository.findUserByEmail(req.getEmail());

    if (userCandidate.isPresent()) {
      throw new IllegalStateException("Login/email is already taken (The USER is already exist)");
    }

    String passHash = jwtService.getPassHash(req.getPassword());

    Users user = this.usersService.create(new CreateUserDTO(req.getEmail(), passHash, "USER"))
            .getBody();

    //   id: 8,
    //   email: "admin2@a.a",
    //   roles: [
    //     {
    //       id: 1,
    //       value: "ADMIN",
    //       description: "Administrator",
    //       createdAt: "2023-06-29T12:12:53.132Z",
    //       updatedAt: "2023-06-29T12:12:53.132Z",
    //     },
    //   ],

    Map<String, Object> clime = new HashMap<>();
    clime.put("id", user.getId());
    clime.put("email", user.getEmail());
    clime.put("roles", user.getRoles());

    String jwtToken = jwtService.generateToken(clime, user);

    return new AuthResponse(jwtToken);
  }

  private Users validateUser(AuthRequest req) {
    Users user = this.usersRepository.findUserByEmail(req.getEmail())
            .orElseThrow(() -> new IllegalStateException("User does not exist"));

    if (req.getPassword() != null && req.getPassword().isEmpty()) {
      throw new IllegalStateException("password should not be empty");
    }

    if (! jwtService.isPasswordEqual(req.getPassword(), user.getPassword())) {
      throw new IllegalStateException("password should not be empty");
    }

    return user;
  }
}
