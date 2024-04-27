package com.codeSake.inventoryservices;

import com.codeSake.inventoryservices.model.Inventory;
import com.codeSake.inventoryservices.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServicesApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData (InventoryRepo inventoryRepo){
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("iphone_13");
//			inventory.setQuantity(100);
//
//			Inventory inventory1= new Inventory();
//			inventory1.setSkuCode("iphone_15");
//			inventory1.setQuantity(0);
//
//			inventoryRepo.save(inventory1);
//			inventoryRepo.save(inventory);
//
//		};
//	}

}
