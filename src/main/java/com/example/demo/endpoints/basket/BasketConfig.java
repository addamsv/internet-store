package com.example.demo.endpoints.basket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BasketConfig {
    @Bean
    CommandLineRunner basketCommandLineRunner(BasketRepository basketRepository) {
        return args -> {
            Basket d1 = new Basket();

            Basket d2 = new Basket();

            basketRepository.saveAll(List.of(d1, d2));
        };
    }
}
