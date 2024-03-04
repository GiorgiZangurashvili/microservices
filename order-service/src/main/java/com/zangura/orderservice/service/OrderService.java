package com.zangura.orderservice.service;

import com.zangura.orderservice.domain.Order;
import com.zangura.orderservice.domain.OrderLineItems;
import com.zangura.inventoryservice.dto.InventoryResponse;
import com.zangura.orderservice.dto.OrderRequest;
import com.zangura.orderservice.event.OrderPlacedEvent;
import com.zangura.inventoryservice.exception.ItemNotInStockException;
import com.zangura.orderservice.mapper.OrderLineItemsMapper;
import com.zangura.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.core.KafkaTemplate;
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
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsMapper orderLineItemsMapper;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItemsMapper
                .mapDtosToEntities(orderRequest.getOrderLineItemsDTOList()));

        List<String> skuCodes = order
                .getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        log.info("Calling inventory-service from order-service");

        Span inventoryServiceCheckSpan = tracer.nextSpan().name("InventoryServiceCheckSpan");

        try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceCheckSpan.start())) {
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
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                return "Order placed successfully";
            } else {
                throw new ItemNotInStockException();
            }
        } finally {
            inventoryServiceCheckSpan.end();
        }
    }
}
