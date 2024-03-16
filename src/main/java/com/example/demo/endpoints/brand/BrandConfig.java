package com.example.demo.endpoints.brand;

import com.example.demo.endpoints.type.Type;
import com.example.demo.endpoints.type.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BrandConfig {
    @Bean
    CommandLineRunner brandCommandLineRunner(BrandRepository brandRepository) {
        return (args) -> {
            Brand mi = new Brand("Mi");

            Brand iPhone = new Brand("iPhone");

            brandRepository.saveAll(List.of(mi, iPhone));
        };
    }
}
