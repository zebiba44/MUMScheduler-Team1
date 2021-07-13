package com.mumSchud.springbootmum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mumSchud.springbootmum.repository.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = StudentRepository.class)
public class SpringBootMumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMumApplication.class, args);
	}
}
