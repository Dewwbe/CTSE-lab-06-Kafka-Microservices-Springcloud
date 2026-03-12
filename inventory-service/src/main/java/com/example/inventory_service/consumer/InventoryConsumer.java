package com.example.inventoryservice.consumer;

import com.example.inventoryservice.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {

    @KafkaListener(topics = "order-topic", groupId = "inventory-group")
    public void consume(OrderEvent event) {
        System.out.println("Inventory Service received order: " + event.getOrderId());
        System.out.println("Updating stock for item: " + event.getItem() + ", quantity: " + event.getQuantity());
    }
}