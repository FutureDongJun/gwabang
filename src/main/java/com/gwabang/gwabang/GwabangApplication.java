package com.gwabang.gwabang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.gwabang.gwabang")
@EntityScan(basePackages = "com.gwabang.gwabang")
public class GwabangApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwabangApplication.class, args);
	}

}
