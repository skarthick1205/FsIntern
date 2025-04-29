package com.example.RabbitMQ.model;


import java.io.Serializable;

public class Order implements Serializable {
    private String orderId;
    private String product;

    // Getters, setters, constructors

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
