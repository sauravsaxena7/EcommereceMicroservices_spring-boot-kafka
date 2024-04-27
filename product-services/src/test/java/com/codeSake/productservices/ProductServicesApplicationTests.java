package com.codeSake.productservices;

import com.codeSake.productservices.dto.ProductRequest;
import com.codeSake.productservices.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServicesApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.4");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;


	//this is for converting pojo obj to json or json to pojo obj
	@Autowired
	private ObjectMapper objectMapper;
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);

	}


//	@Test
//	void contextLoads() {
//	}

	//JsonProcessingException
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();

		String productRequestStr=objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestStr)//only take only type of string
		).andExpect(status().isCreated());

		//Assertions.assertTrue(productRepository.findAll().size()==1);
        Assertions.assertEquals(1, productRepository.findAll().size());

	}



	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("LOLA")
				.description("Lola 13")
				.price(BigDecimal.valueOf(1900))
				.build();
	}


}
