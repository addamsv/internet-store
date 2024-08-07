package com.example.demo.endpoints.users;

import com.example.demo.endpoints.role.Role;
import com.example.demo.endpoints.role.RoleRepository;
import com.example.demo.guard.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class UsersConfig {
    private final JwtService jwtService;

    @Autowired
    public UsersConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository, RoleRepository roleRepository) {
        return args -> {
            Role role = roleRepository.save(
                new Role("ADMIN", "Administrator")
            );

            Users user = new Users(
                "sergy@a.a",
                jwtService.getPassHash("Sergey-aaa")
            );

            user.setRoles(List.of(role));
            user.setActivationLink(UUID.randomUUID().toString());

            // Next line will insert on USERS and ROLE_USERS tables
            user = usersRepository.saveAndFlush(user);

            // We add the category to the ad object to keep both sides in sync
            role.setUsers(List.of(user));





            user = new Users(
                    "a",
                    jwtService.getPassHash("a")
            );

            user.setRoles(List.of(role));
            user.setActivationLink(UUID.randomUUID().toString());

            // Next line will insert on USERS and ROLE_USERS tables
            user = usersRepository.saveAndFlush(user);

            // We add the category to the ad object to keep both sides in sync
            role.setUsers(List.of(user));




            role = roleRepository.save(
                    new Role("USER", "Average User")
            );

            user = new Users(
                    "alex@a.a",
                    jwtService.getPassHash("aaa")
            );

            user.setRoles(List.of(role));
            user.setActivationLink(UUID.randomUUID().toString());

            // Next line will insert on USERS and ROLE_USERS tables
            user = usersRepository.saveAndFlush(user);

            // We add the category to the ad object to keep both sides in sync
            role.setUsers(List.of(user));

        };
    }
}
