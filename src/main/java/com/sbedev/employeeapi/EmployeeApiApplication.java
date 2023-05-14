package com.sbedev.employeeapi;

import com.sbedev.employeeapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeApiApplication implements CommandLineRunner {

	@Autowired
	ClientService clientService;


	public static void main(String[] args) {
		SpringApplication.run(EmployeeApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		createClient();
	}

	public void createClient(){




	}

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












