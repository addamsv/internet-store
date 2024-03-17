package com.example.demo.guard.roles;

import com.example.demo.guard.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolesGuard {
    private final JwtService jwtService; // private reflector: Reflector

    @Autowired
    public RolesGuard(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public boolean canActivate(String token) {
        // https://www.baeldung.com/role-and-privilege-for-spring-security-registration
        String role = this.jwtService.extractRole(token);

        return role.equals("ADMIN"); // return user.roles.some((role) => requiredRoles.includes(role.value));
    }
}