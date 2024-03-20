package com.example.demo.guard.jwt;

import com.example.demo.endpoints.roles.Role;
import com.example.demo.endpoints.roles.RoleRepository;
import com.example.demo.endpoints.users.Users;
import com.example.demo.endpoints.users.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public JwtAuthenticationFilter(
        JwtService jwtService,
        UsersRepository usersRepository,
        RoleRepository roleRepository
    ) {
        this.jwtService = jwtService;
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        final String jwt;

        final String userEmail;

        final String userRole;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        userRole = jwtService.extractRole(jwt);

        if (
                !userEmail.isEmpty()
                && SecurityContextHolder.getContext().getAuthentication() == null
        ) {
            Users userDetails = usersRepository.findUserByEmail(userEmail)
                    .orElse(new Users());

            Role role = roleRepository.findRoleByValue(userRole)
                    .orElse(new Role());

            System.out.println(role);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }

        filterChain.doFilter(request, response);
    }
}
