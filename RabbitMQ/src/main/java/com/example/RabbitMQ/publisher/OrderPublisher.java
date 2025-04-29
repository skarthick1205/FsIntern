package com.example.RabbitMQ.publisher;

import com.example.RabbitMQ.config.RabbitMQConfig;
import com.example.RabbitMQ.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, order);
        System.out.println("Order sent to RabbitMQ: " + order.getOrderId());
    }
}
