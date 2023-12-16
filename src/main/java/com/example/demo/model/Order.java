package com.example.demo.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Order {
    private Long id;
    private String orderNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date orderDate;
    private double totalAmount;
    public Order() {
    }

    public Order(Long id, String orderNumber, Date orderDate, double totalAmount) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
