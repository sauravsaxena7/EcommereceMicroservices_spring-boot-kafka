package com.codeSake.productservices.repository;

import com.codeSake.productservices.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

//ist parameter is POJO MODEL CLASS
//2ND PARAMENTER IS @ID TYPE IS STRING
public interface ProductRepository extends MongoRepository<Product,String> {

}
