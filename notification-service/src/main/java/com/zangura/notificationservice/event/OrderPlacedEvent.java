package com.zangura.notificationservice.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderPlacedEvent {
    private String orderNumber;
}
