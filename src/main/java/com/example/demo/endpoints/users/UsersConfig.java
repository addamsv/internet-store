package com.example.demo.endpoints.users;

import com.example.demo.guard.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UsersConfig {
    private final JwtService jwtService;
    @Autowired
    public UsersConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
        return args -> {
            Users sergey = new Users(
                    jwtService.getPassHash("Sergey-aaa"),
                    "sergy@a.a"
            );

            Users alexandr = new Users(
                    jwtService.getPassHash("Alex-bbb"),
                    "alex@a.a"
            );

            usersRepository.saveAll(List.of(sergey, alexandr));
        };
    }
}
