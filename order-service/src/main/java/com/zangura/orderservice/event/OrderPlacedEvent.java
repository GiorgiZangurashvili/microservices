package com.zangura.orderservice.event;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderPlacedEvent {
    private String orderNumber;
}
