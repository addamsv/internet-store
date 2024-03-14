package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("https://jwt.io/");
		System.out.println("https://start.spring.io/");
		System.out.println("http://localhost:5500/swagger-ui/index.html");
		System.out.println("http://localhost:5500/api/v1/users");
		System.out.println("ok!");
	}

}
