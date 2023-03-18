package com.nickfistere.airmendatabase.airmencertification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AirmenCertificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirmenCertificationApplication.class, args);
	}

}
