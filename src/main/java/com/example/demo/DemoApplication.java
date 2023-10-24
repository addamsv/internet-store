package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("http://localhost:5000/swagger-ui/index.html");
		System.out.println("http://localhost:5000/api/v1/users");
		System.out.println("ok!");
	}

}
