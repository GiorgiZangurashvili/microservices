package com.zangura.service;

import com.zangura.domain.Order;
import com.zangura.dto.OrderRequest;
import com.zangura.mapper.OrderLineItemsMapper;
import com.zangura.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsMapper orderLineItemsMapper;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItemsMapper
                .mapDtosToEntities(orderRequest.getOrderLineItemsDTOList()));

        orderRepository.save(order);
    }
}
