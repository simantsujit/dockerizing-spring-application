package com.portal.job_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.portal.job_service.repository")
public class JobServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobServiceApplication.class, args);
	}

}
