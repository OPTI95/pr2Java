package com.example.demo.dao;

import com.example.demo.model.Order;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class OrderDao {

    private static Long ORDER_COUNT = 0L;
    private List<Order> orders;

    {
        orders = new ArrayList<>();

        orders.add(new Order(++ORDER_COUNT, "ORD123", new Date(), 150.0));
        orders.add(new Order(++ORDER_COUNT, "ORD456", new Date(), 300.0));
    }

    public List<Order> index() {
        return orders;
    }

    public Order show(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findAny().orElse(null);
    }

    public void save(Order order) {
        order.setId(++ORDER_COUNT);
        orders.add(order);
    }

    public void update(Long id, Order updatedOrder) {
        Order existingOrder = show(id);
        if (existingOrder != null) {
            existingOrder.setOrderNumber(updatedOrder.getOrderNumber());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        }
    }

    public void delete(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }
}
