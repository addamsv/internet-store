package com.example.demo.endpoints.post;

import com.example.demo.endpoints.device.Device;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PostConfig {
    @Bean
    CommandLineRunner postCommandLineRunner(PostRepository postRepository) {
        return args -> {
            Post d1 = new Post(
                    1L,
                    "Standard Title",
                    "Awesome content",
                    "5b9669b2-fa1b-40d6-a479-a1785c513dd6.jpg"
            );

            Post d2 = new Post(
                    2L,
                    "Good Title",
                    "Bright Content",
                    "5b9669b2-fa1b-40d6-a479-a1785c513dd6.jpg"
            );

            postRepository.saveAll(List.of(d1, d2));
        };
    }
}
