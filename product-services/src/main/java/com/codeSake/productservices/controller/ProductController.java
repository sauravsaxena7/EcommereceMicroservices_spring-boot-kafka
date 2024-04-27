package com.codeSake.productservices.controller;

import com.codeSake.productservices.dto.ProductRequest;
import com.codeSake.productservices.dto.ProductResponse;
import com.codeSake.productservices.services.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServices productServices;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  void createProduct(@RequestBody ProductRequest productRequest){
        productServices.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productServices.getAllProducts();
    }



}
