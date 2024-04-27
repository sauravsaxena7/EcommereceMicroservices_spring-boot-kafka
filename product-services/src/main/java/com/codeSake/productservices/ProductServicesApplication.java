package com.codeSake.productservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//this the link for tesContainers for writting junit test
//https://java.testcontainers.org/
@SpringBootApplication
@EnableDiscoveryClient
public class ProductServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServicesApplication.class, args);
	}

}
