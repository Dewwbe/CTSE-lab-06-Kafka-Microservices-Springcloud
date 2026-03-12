package com.example.billingservice.consumer;

import com.example.billingservice.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BillingConsumer {

    @KafkaListener(topics = "order-topic", groupId = "billing-group")
    public void consume(OrderEvent event) {
        System.out.println("Billing Service received order: " + event.getOrderId());
        System.out.println("Generating invoice for item: " + event.getItem() + ", quantity: " + event.getQuantity());
    }
}