package com.ricecakesoup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RiceCakeSoupApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiceCakeSoupApplication.class, args);
	}

}
