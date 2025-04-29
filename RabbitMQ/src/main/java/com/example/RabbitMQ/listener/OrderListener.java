package com.example.RabbitMQ.listener;

import com.example.RabbitMQ.config.RabbitMQConfig;
import com.example.RabbitMQ.model.Order;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveOrder(Order order) {
        System.out.println("Received Order: " + order.getOrderId() + ", Product: " + order.getProduct());
    }
}
