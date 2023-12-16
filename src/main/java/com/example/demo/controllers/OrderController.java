package com.example.demo.controllers;

import com.example.demo.dao.OrderDao;
import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping
    public String showAllOrders(Model model) {
        List<Order> orders = orderDao.index();
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @GetMapping("/{id}")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        Order order = orderDao.show(id);
        model.addAttribute("order", order);
        return "order/orderDetails";
    }

    @GetMapping("/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/orderForm";
    }

    @PostMapping
    public String createOrder(@ModelAttribute Order order) {
        orderDao.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = orderDao.show(id);
        model.addAttribute("order", order);
        return "order/editOrderForm";
    }

    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order updatedOrder) {
        orderDao.update(id, updatedOrder);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderDao.delete(id);
        return "redirect:/orders";
    }
}
