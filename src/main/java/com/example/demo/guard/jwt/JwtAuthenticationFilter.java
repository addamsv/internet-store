package com.example.demo.guard.jwt;

import com.example.demo.endpoints.users.Users;
import com.example.demo.endpoints.users.UsersRepository;
import com.example.demo.exceptions.AuthException;
import io.jsonwebtoken.ExpiredJwtException;
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

import static com.example.demo.utils.PrintEx.printTitle;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsersRepository usersRepository;

    @Autowired
    public JwtAuthenticationFilter(
        JwtService jwtService,
        UsersRepository usersRepository
    ) {
        this.jwtService = jwtService;
        this.usersRepository = usersRepository;
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

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        printTitle("JwtAuthenticationFilter");
        System.out.println(jwt);

        if (jwt.equals("null") || jwt.equals("undefined")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            userEmail = jwtService.extractUsername(jwt);
        } catch (ExpiredJwtException e) {
            throw new AuthException(e.getMessage());
        }

        if (
            userEmail != null && ! userEmail.isEmpty()
            && SecurityContextHolder.getContext().getAuthentication() == null
        ) {
            Users userDetails = usersRepository.findUserByEmail(userEmail)
                    .orElse(new Users());

            try {
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
            } catch (ExpiredJwtException e) {
                throw new AuthException(e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
