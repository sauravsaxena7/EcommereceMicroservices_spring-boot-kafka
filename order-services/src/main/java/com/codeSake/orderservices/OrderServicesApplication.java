package com.codeSake.orderservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class  OrderServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServicesApplication.class, args);
	}

}
