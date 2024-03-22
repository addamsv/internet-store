package com.example.demo.endpoints.rating;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RatingConfig {
    @Bean
    CommandLineRunner ratingCommandLineRunner(RatingRepository ratingRepository) {
        return args -> {
            Rating d1 = new Rating(
                    1L,
                    0
            );

            Rating d2 = new Rating(
                    2L,
                    10
            );

            ratingRepository.saveAll(List.of(d1, d2));
        };
    }
}
