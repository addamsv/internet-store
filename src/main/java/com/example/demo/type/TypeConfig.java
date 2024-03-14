package com.example.demo.type;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TypeConfig {
    @Bean
    CommandLineRunner typeCommandLineRunner(TypeRepository typeRepository) {
        return args -> {
            Type fridge = new Type("Fridges");

            Type phone = new Type("Phones");

            typeRepository.saveAll(List.of(fridge, phone));
        };
    }
}
