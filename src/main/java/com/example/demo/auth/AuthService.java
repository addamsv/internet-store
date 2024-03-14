package com.example.demo.auth;

import com.example.demo.users.Users;
import com.example.demo.users.UsersRepository;
import com.example.demo.users.UsersService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
  private final UsersRepository usersRepository;
  private final JwtService jwtService;
  private final UsersService usersService;

  public AuthService (
          UsersRepository usersRepository,
          UsersService usersService,
          JwtService jwtService

  ) {
    this.usersRepository = usersRepository;
    this.jwtService = jwtService;
    this.usersService = usersService;
  }

  public AuthResponse login(AuthRequest req) {
    var user = this.validateUser(req);
    var jwtToken = jwtService.generateToken(user);
    return new AuthResponse(jwtToken);
  }

  public AuthResponse registration(AuthRequest req) {
    Optional<Users> userCandidate = usersRepository.findUserByEmail(req.getEmail());

    if (userCandidate.isPresent()) {
      throw new IllegalStateException("Login/email is already taken (The USER is already exist)");
    }

//    var passHash =  bcrypt.hash(req.password, 5);

    Users userEntity = new Users(req.getPassword(), req.getEmail());
    var user = this.usersService.addUser(userEntity);


    var jwtToken = jwtService.generateToken(user);
    return new AuthResponse(jwtToken);
  }

  private Users validateUser(AuthRequest req) {
    Users userCandidate = this.usersRepository.findUserByEmail(req.getEmail())
            .orElseThrow(() -> new IllegalStateException("User does not exist"));

    if (
            req.getPassword() != null
            && !req.getPassword().isEmpty()
            && !Objects.equals(userCandidate.getPassword(), req.getPassword())
    ) {
      throw new IllegalStateException("Wrong password");
    }

    if (
            req.getEmail() != null
            && !req.getEmail().isEmpty()
            && !Objects.equals(userCandidate.getEmail(), req.getEmail())
    ) {
      throw new IllegalStateException("Wrong login");
    }

//    var isPasswordEqual = bcrypt.compare(users.password);

    return userCandidate;

  }
}
