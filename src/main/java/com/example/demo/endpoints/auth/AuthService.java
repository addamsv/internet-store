package com.example.demo.endpoints.auth;

import com.example.demo.endpoints.role.Role;
import com.example.demo.endpoints.role.RoleRepository;
import com.example.demo.endpoints.users.Users;
import com.example.demo.endpoints.users.UsersRepository;
import com.example.demo.guard.jwt.JwtService;
import com.example.demo.guard.roles.RolesGuard;
import com.example.demo.mail.MailService;
import com.example.demo.utils.PrintEx;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthService {
  @Value("${spring.mail.is.activation}")
  private Boolean isMailActivation;

  @Value("${server.domain}")
  private String serverDomain;

  private final UsersRepository usersRepository;
  private final RoleRepository roleRepository;
  private final JwtService jwtService;
  private final MailService mailService;

  @Autowired
  public AuthService (
      UsersRepository usersRepository,
      RoleRepository roleRepository,
      MailService mailService,
      JwtService jwtService,
      RolesGuard rolesGuard
  ) {
    this.usersRepository = usersRepository;
    this.roleRepository = roleRepository;
    this.jwtService = jwtService;
    this.mailService = mailService;
  }

  public AuthResponse login(AuthRequest req) {
    Users user = this.validateUser(req);

    //   {id: 8, email: "admin2@a.a", roles: [ { id: 1, value: "ADMIN", description: "Administrator" } ] }

    Map<String, Object> clime = new HashMap<>();
    clime.put("id", user.getId());
    clime.put("email", user.getEmail());
    clime.put("isActivated", user.getActivated());
    clime.put("roles", user.getRoles());

    String jwtToken = jwtService.generateToken(clime, user);

    String jwtRefreshToken = jwtService.generateRefreshToken(clime, user);

    user.setRefreshToken(jwtRefreshToken);

    usersRepository.save(user);

    return new AuthResponse(jwtToken, jwtRefreshToken);
  }

  @Transactional
  public AuthResponse registration(AuthRequest req) {
    Optional<Users> userCandidate = usersRepository.findUserByEmail(req.getEmail());

    if (userCandidate.isPresent()) {
      throw new IllegalStateException("Login/email is already taken (The USER is already exist)");
    }

    String hashPass = jwtService.getPassHash(req.getPassword());

    Role role = roleRepository.findRoleByValue("USER").orElse(new Role(7777777777L,"USER-COMMON", "AVERAGE"));

    Users userCandidateToSave = new Users(req.getEmail(), hashPass);

    String activationLink = UUID.randomUUID().toString();

    userCandidateToSave.setRoles(List.of(role));
    userCandidateToSave.setActivationLink(activationLink);

    /* Next line will insert on USERS and ROLE_USERS tables */
    Users user = usersRepository.saveAndFlush(userCandidateToSave);

    /* We add the category to the ad object to keep both sides in sync */
    role.setUsers(List.of(userCandidateToSave));

    /* sending activation email */
    if (isMailActivation) {
        mailService.sendActivationEmail(user.getEmail(), "http://" + serverDomain + "/api/v1/activate/" + activationLink);
    }

    Map<String, Object> clime = new HashMap<>();
    clime.put("id", user.getId());
    clime.put("email", user.getEmail());
    clime.put("isActivated", user.getActivated());
    clime.put("roles", user.getRoles());

    String jwtToken = jwtService.generateToken(clime, user);

    String jwtRefreshToken = jwtService.generateRefreshToken(clime, user);

    user.setRefreshToken(jwtRefreshToken);

    return new AuthResponse(jwtToken, jwtRefreshToken);
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

  @Transactional
  public ResponseEntity<String> activate(String link) {
    Users user = this.usersRepository.findByActivationLink(link)
            .orElse(null);

    if (user == null) {
      return new ResponseEntity<>("User does not exist or link expired", HttpStatus.NOT_FOUND);
    }

    user.setActivated(true);
    user.setActivationLink("");

    return  new ResponseEntity<>("User is successfully activated", HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<String> logout(String refreshToken) {
    Users user = this.usersRepository.findByRefreshToken(refreshToken)
            .orElse(null);

    if (user == null) {
      return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
    }

    user.setRefreshToken("");

    return new ResponseEntity<>("User successfully unauthorised", HttpStatus.OK);
  }

  @Transactional
  public AuthResponse refresh(String refreshToken) {
    if (refreshToken == null || refreshToken.isEmpty()) {

      PrintEx.printTitle("User is not authorized [1]");

      throw new IllegalStateException("User is not authorized");
    }

    PrintEx.printTitle(refreshToken);

    Users user = this.usersRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalStateException("User is not authorized"));

    if (! jwtService.isRefreshTokenValid(refreshToken, user)) {

      PrintEx.printTitle("User is not authorized [2]");

      throw new IllegalStateException("User is not authorized");
    }

    Map<String, Object> clime = new HashMap<>();
    clime.put("id", user.getId());
    clime.put("email", user.getEmail());
    clime.put("isActivated", user.getActivated());
    clime.put("roles", user.getRoles());

    user.setRefreshToken("");

    String jwtToken = jwtService.generateToken(clime, user);

    String jwtRefreshToken = jwtService.generateRefreshToken(clime, user);

    user.setRefreshToken(jwtRefreshToken);

    return new AuthResponse(jwtToken, jwtRefreshToken);
  }

  public void setRefreshToken(String token, HttpServletResponse response) {
    Cookie cookie = new Cookie("refresh-token", token);

    cookie.setMaxAge(604800000); // 7 days
    cookie.setHttpOnly(true);
    cookie.setPath("/api/v1/auth");
//    cookie.setSecure(true);
//    cookie.setDomain("example.com");

    response.addCookie(cookie);
  }

  public void remRefreshToken(HttpServletResponse response) {
    Cookie cookie = new Cookie("refresh-token", null);

    cookie.setMaxAge(0); // 0 millisecond
    cookie.setHttpOnly(true);
    cookie.setPath("/api/v1/auth");
//    cookie.setSecure(true);
//    cookie.setDomain("example.com");

    response.addCookie(cookie);
  }
}
