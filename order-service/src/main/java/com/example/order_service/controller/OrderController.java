package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderController(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderEvent orderEvent) {
        kafkaTemplate.send("order-topic", orderEvent);
        System.out.println("Order Created: " + orderEvent.getOrderId());
        return "Order Created & Event Published";
    }
}