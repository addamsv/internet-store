package com.example.demo;

import com.example.demo.endpoints.DTO.RespDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("https://jwt.io/");
		System.out.println("https://start.spring.io/");
		System.out.println("http://localhost:5500/swagger-ui/index.html");
		System.out.println("http://localhost:5500/api/v1/users");
		System.out.println("http://localhost:5500/index.html");
		System.out.println("http://localhost:5500/");
		System.out.println("ok!");
	}


//	@Bean
//	public CommandLineRunner appCommandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}
}
