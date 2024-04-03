package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

//		Map<String, String> env = System.getenv();
//		for (String envName : env.keySet()) {
//			System.out.format("%s=%s%n", envName, env.get(envName));
//		}

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
