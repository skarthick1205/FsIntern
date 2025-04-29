package com.example.RabbitMQ.controller;


import com.example.RabbitMQ.model.Order;
import com.example.RabbitMQ.publisher.OrderPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderPublisher publisher;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        publisher.sendOrder(order);
        return ResponseEntity.ok("Order placed successfully!");
    }
}
