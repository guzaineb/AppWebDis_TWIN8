package com.esprit.microservice.eureka_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaOrderApplication.class, args);
	}

}
