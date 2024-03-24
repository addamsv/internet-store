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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
    private static final List<String> CORS_LIST_URL = List.of(
            "http://localhost:3000"
    );

    private static final String[] GET_WHITE_LIST_URL = {
            "/api/v1/rating/**",
            "/api/v1/device/**",
            "/api/v1/posts/**",
            "/api/v1/type/**",
            "/api/v1/brand/**",
            "/api/v1/users/check/**"
    };

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
            "/api/v1/post/**",
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


    public CorsConfigurationSource corsConf() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //Make the below setting as * to allow connection from any hos
        corsConfiguration.setAllowedOrigins(CORS_LIST_URL);
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(conf -> conf.configurationSource(corsConf()))
        .authorizeHttpRequests(req ->
            req
              .requestMatchers(WHITE_LIST_URL)
                .permitAll()
              .requestMatchers(HttpMethod.GET, GET_WHITE_LIST_URL)
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
            .logout((logOut) ->
              logOut
                .logoutUrl("/api/v1/auth/logout").permitAll()
                  .invalidateHttpSession(true)
                    .deleteCookies("I-STORE")
                      .logoutSuccessHandler((req, resp, auth) -> SecurityContextHolder.clearContext())
            );

        return http.build();
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService() {
        return username -> usersRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}