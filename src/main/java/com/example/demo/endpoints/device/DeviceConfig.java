package com.example.demo.endpoints.device;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DeviceConfig {
    @Bean
    CommandLineRunner deviceCommandLineRunner(DeviceRepository deviceRepository) {
        return args -> {
            Device d1 = new Device(
                    1L,
                    "Telipon",
                    1000,
                    10,
                    "x"
            );

            Device d2 = new Device(
                    2L,
                    "Smartphone",
                    1200,
                    10,
                    "x"
            );

            deviceRepository.saveAll(List.of(d1, d2));
        };
    }
}
