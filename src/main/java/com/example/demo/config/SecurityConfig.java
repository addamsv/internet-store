package com.example.demo.config;

import com.example.demo.endpoints.users.UsersRepository;
import com.example.demo.guard.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/*",
//            "/*.htm",
//            "/*.html",
            "/images/**",
            "/js/**",
            "/css/**",

            "/api/v1/auth/**",
            "/api/v1/order/**",

            "/api/v1/device/**",
            "/api/v1/posts/**",
//            "/api/v1/users/**",
//            "/api/v1/brand/**",
//            "/api/v1/type/**",

            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UsersRepository usersRepository;

    @Autowired
    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthFilter,
            UsersRepository usersRepository
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.usersRepository = usersRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req ->
            req
            .requestMatchers(WHITE_LIST_URL)
                .permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/rating/**")
                .permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/device/**")
                .permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/posts/**")
                .permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/type/**")
                .permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/brand/**")
                .permitAll()
    //            .requestMatchers("/api/v1/users/**")
    //                .hasAnyRole("ADMIN", "MANAGER")
    //            .requestMatchers(GET, "/api/v1/users/**")
    //                .hasAnyAuthority("ADMIN_READ", "MANAGER_READ")
    //            .requestMatchers(POST, "/api/v1/users/**")
    //                .hasAnyAuthority("ADMIN_CREATE", "MANAGER_CREATE")
    //            .requestMatchers(PUT, "/api/v1/users/**")
    //                .hasAnyAuthority("ADMIN_UPDATE", "MANAGER_UPDATE")
    //            .requestMatchers(DELETE, "/api/v1/users/**")
    //                .hasAnyAuthority("ADMIN_DELETE", "MANAGER_DELETE")
            .anyRequest().authenticated()
        )
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(LogoutConfigurer::permitAll
//                logout.logoutUrl("/api/v1/auth/logout")
//                    .addLogoutHandler(logoutHandler())
//                    .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
            );

        return http.build();
    }

//    private LogoutHandler logoutHandler() {
//        return null;
//    }

//    @Bean
//    public LogoutHandler logoutHandler() {
//        return new LogoutHandler() {
//            @Override
//            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//                return;
//            }
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usersRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());//
        authProvider.setPasswordEncoder(passwordEncoder());//
        return authProvider;
    }
}