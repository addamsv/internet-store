package com.example.demo.guard.roles;

import com.example.demo.endpoints.device.dto.DeviceInfoDTO;
import com.example.demo.endpoints.role.Role;
import com.example.demo.guard.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class RolesGuard {
    private final JwtService jwtService;

    @Autowired
    public RolesGuard(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // https://www.baeldung.com/role-and-privilege-for-spring-security-registration

    public boolean canActivate(String token) {
        List<Role> userRoles = this.jwtService.extractRole(token);

        boolean retValue = true;

        // return user.roles.some((role) => requiredRoles.includes(role.value));

        String[] requiredRoles = {"ADMIN"};

        for (Role userRole : userRoles) {
            if (
                    userRole.getValue() != null
                    && ! userRole.getValue().isEmpty()
                    && Arrays.asList(requiredRoles).contains(userRole.getValue())
            ) {
                retValue = false;
                break;
            }
        }

        return retValue;
    }
}