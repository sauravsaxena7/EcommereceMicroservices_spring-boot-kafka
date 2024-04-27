package com.codeSake.productservices.services;


import com.codeSake.productservices.dto.ProductRequest;
import com.codeSake.productservices.dto.ProductResponse;
import com.codeSake.productservices.model.Product;
import com.codeSake.productservices.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServices {

    private final ProductRepository productRepository;
//    public ProductServices(ProductRepository productRepository){
//        this.productRepository=productRepository;
//    }

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("PRODUCT {} IS SAVED.",product.getId());


    }


    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        
        //products.stream().map(product -> mapToProductResponse(product))
        return products.stream().map(this::mapToProductResponse).toList();
        
    }

    private ProductResponse mapToProductResponse(Product product) {
      return ProductResponse.builder()
              .id(product.getId())
              .description(product.getDescription())
              .price(product.getPrice())
              .name(product.getName())
              .build();
    }
}
