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
                    "5b9669b2-fa1b-40d6-a479-a1785c513dd6.jpg"
            );

            Device d2 = new Device(
                    2L,
                    "Smartphone",
                    1200,
                    10,
                    "5b9669b2-fa1b-40d6-a479-a1785c513dd6.jpg"
            );

            deviceRepository.saveAll(List.of(d1, d2));
        };
    }
}
