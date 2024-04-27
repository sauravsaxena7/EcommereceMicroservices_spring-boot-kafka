package com.codeSake.orderservices.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    //Here what happens if we create multiple server of any instances
    // like inventory-services or product services
    //Then webclient get confused after seeing multiple instances then we need to
    //use load balancer annotations here for handling above problem

//    @Bean
//    public WebClient webClient(){
//        return WebClient.builder().build();
//    }


    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

}
