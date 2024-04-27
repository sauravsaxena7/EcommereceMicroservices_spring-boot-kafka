package com.codeSake.inventoryservices.services;


import com.codeSake.inventoryservices.dto.InventoryResponse;
import com.codeSake.inventoryservices.repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServices {
    private final InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes){
        return inventoryRepo.findBySkuCodeIn(skuCodes)
                .stream().map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()
                ).toList();

    }
}
