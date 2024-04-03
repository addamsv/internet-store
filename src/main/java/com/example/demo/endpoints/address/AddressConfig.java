package com.example.demo.endpoints.address;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AddressConfig {
    @Bean
    CommandLineRunner addressCommandLineRunner(AddressRepository addressRepository) {
        return args -> {
            addressRepository.saveAll(List.of(
                new Address("Pravda", "60", "2220116"),
                new Address("Pravda", "30", "2220116")
            ));
        };
    }
}
