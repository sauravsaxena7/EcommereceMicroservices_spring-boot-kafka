package com.codeSake.orderservices.controllers;


import com.codeSake.orderservices.dto.OrderRequest;
import com.codeSake.orderservices.services.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest){

        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully!";
    }


    public String fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return "Oops! Something went wrong, Please order after some time.";
    }
}
