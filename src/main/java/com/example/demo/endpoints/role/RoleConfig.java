package com.example.demo.endpoints.role;
import com.example.demo.endpoints.users.Users;
import com.example.demo.endpoints.users.UsersRepository;
import com.example.demo.guard.jwt.JwtService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RoleConfig {
    @Bean
    CommandLineRunner roleCommandLineRunner(RoleRepository roleRepository) {
        return args -> {
           roleRepository.saveAll(List.of(
                   new Role("MANAGER", "Manager"),
                   new Role("MANAGER_READ", "Manager"),
                   new Role("MANAGER_CREATE", "Manager"),
                   new Role("MANAGER_UPDATE", "Manager"),
                   new Role("MANAGER_DELETE", "Manager"),
                   new Role("ADMIN_READ", "Administrator"),
                   new Role("ADMIN_CREATE", "Administrator"),
                   new Role("ADMIN_UPDATE", "Administrator"),
                   new Role("ADMIN_DELETE", "Administrator")
           ));
        };
    }
}
