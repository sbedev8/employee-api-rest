package com.sbedev.employeeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EmployeeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApiApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**")
//						.allowedOrigins("http://localhost:4200") //
//						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
//						.allowedHeaders("*")
//						.allowCredentials(true);
//			}
//		};
//	}

}
