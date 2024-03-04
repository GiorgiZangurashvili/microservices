package com.zangura.notificationservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zangura.notificationservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(String event) {
        try {
            OrderPlacedEvent orderPlacedEvent = objectMapper.readValue(event, OrderPlacedEvent.class);
            log.info("Received notification for order - {}", orderPlacedEvent.getOrderNumber());
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON: {}", e.getMessage());
        }
    }
}
