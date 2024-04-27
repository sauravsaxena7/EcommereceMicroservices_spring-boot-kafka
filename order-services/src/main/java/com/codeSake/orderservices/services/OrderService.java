package com.codeSake.orderservices.services;

import com.codeSake.orderservices.dto.InventoryResponse;
import com.codeSake.orderservices.dto.OrderLineItemsDto;
import com.codeSake.orderservices.dto.OrderRequest;
import com.codeSake.orderservices.model.Order;
import com.codeSake.orderservices.model.OrderLineItems;
import com.codeSake.orderservices.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    //Here need constructor to initialize this object

    private final OrderRepository orderRepository;
//    private final WebClient webClient;
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        order.setOrderLineItemsList(
                orderRequest.getOrderLineItemsDtoList()
                        .stream()
                        .map(this::mapToDto).toList()
        );


        //call inventory services, and place order if
        //product is in stock

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode).toList();

       InventoryResponse[] inventoryResponses= webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCodes",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();//to call web services synchronously




        Boolean allProductInStock=Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        log.info(Arrays.toString(inventoryResponses)+" allproductinstock "+allProductInStock);
        if(Boolean.TRUE.equals(allProductInStock)){
            orderRepository.save(order);
        }else{
           throw new IllegalArgumentException("Product is not in stock, Please Try Again later.");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());

        return orderLineItems;

    }
}
