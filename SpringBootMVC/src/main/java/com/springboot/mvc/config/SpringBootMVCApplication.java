package com.springboot.mvc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.springboot.mvc")
@EnableJpaRepositories("com.springboot.mvc.repository")
@EntityScan("com.springboot.mvc.entity")
public class SpringBootMVCApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMVCApplication.class, args);
	}

}
