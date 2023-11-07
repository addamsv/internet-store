package com.example.demo.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
        return args -> {
            Users sergey = new Users(
                    "Sergey-aaa",
                    "sergy@a.a"
            );

            Users alexandr = new Users(
                    "Alex-bbb",
                    "alex@a.a"
            );

            usersRepository.saveAll(List.of(sergey, alexandr));
        };
    }
}
