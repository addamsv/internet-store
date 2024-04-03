package com.example.demo.endpoints.device;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DeviceConfig {
    @Bean
    CommandLineRunner deviceCommandLineRunner(DeviceRepository deviceRepository) {
        return (args) -> {
            deviceRepository.saveAll(List.of(
                new Device(
                    "iPhone 12",
                    1000,
                    10,
                    "images/5b9669b2-fa1b-40d6-a479-a1785c513dd6.jpg"
                ),
                new Device(
                    "Mi 5",
                    1200,
                    10,
                    "images/4c50ddf1-5c4c-4f94-93d8-feac2d6a5877.jpg"
                ),
                new Device(
                    "Xi 1",
                    1200,
                    10,
                    "images/5b9669b2-fa1b-40d6-a479-a1785c513dd6.jpg"
                ),
                new Device(
                    "Samsung S1",
                    1200,
                    10,
                    "images/5b9669b2-fa1b-40d6-a479-a1785c513dd6.jpg"
                )
            ));
        };
    }
}
