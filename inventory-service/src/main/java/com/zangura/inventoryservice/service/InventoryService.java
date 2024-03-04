package com.zangura.inventoryservice.service;

import com.zangura.inventoryservice.dto.InventoryResponse;
import com.zangura.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        log.info("Checking if items are in stock");
        return inventoryRepository.findBySkuCodeIn(skuCodes)
                .stream()
                .map(inventory -> InventoryResponse
                       .builder()
                       .skuCode(inventory.getSkuCode())
                       .isInStock(inventory.getQuantity() > 0)
                       .build())
                .collect(Collectors.toList());
    }
}
