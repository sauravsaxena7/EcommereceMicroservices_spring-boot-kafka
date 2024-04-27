package com.codeSake.inventoryservices.controller;

import com.codeSake.inventoryservices.dto.InventoryResponse;
import com.codeSake.inventoryservices.services.InventoryServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServices inventoryServices;

    //http://localhost:8082/api/inventory/iphone-13,iphone13-red

    //http://localhost:8082/api/inventory?sku-code=iphone-13&sku-code=iphone13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCodes") List<String> skuCodes){

        log.info("skucodes "+skuCodes.toString());

        return inventoryServices.isInStock(skuCodes);
    }
}
