package com.zangura.service;

import com.zangura.domain.Order;
import com.zangura.domain.OrderLineItems;
import com.zangura.dto.InventoryResponse;
import com.zangura.dto.OrderRequest;
import com.zangura.exception.ItemNotInStockException;
import com.zangura.mapper.OrderLineItemsMapper;
import com.zangura.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsMapper orderLineItemsMapper;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItemsMapper
                .mapDtosToEntities(orderRequest.getOrderLineItemsDTOList()));

        List<String> skuCodes = order
                .getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        InventoryResponse[] inventoryResponses = webClientBuilder
                .build()
                .get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean isInStock = Arrays
                .stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

        if (isInStock) {
            orderRepository.save(order);
        } else {
            throw new ItemNotInStockException();
        }
    }
}
