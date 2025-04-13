package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.*"})
@EntityScan("com.model")
@EnableJpaRepositories("com.repository")
public class SpringSecurityJwtMysqldbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtMysqldbApplication.class, args);
	}
}
