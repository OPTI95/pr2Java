package com.example.demo.controllers;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @GetMapping
    public String showAllCustomers(Model model) {
        List<Customer> customers = customerDao.index();
        model.addAttribute("customers", customers);
        return "customer/customerList";
    }

    @GetMapping("/{id}")
    public String showCustomerDetails(@PathVariable Long id, Model model) {
        Customer customer = customerDao.show(id);
        model.addAttribute("customer", customer);
        return "customer/customerDetails";
    }

    @GetMapping("/new")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/customerForm";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        customerDao.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerDao.show(id);
        model.addAttribute("customer", customer);
        return "customer/editCustomerForm";
    }

    @PostMapping("/{id}/edit")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer updatedCustomer) {
        customerDao.update(id, updatedCustomer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerDao.delete(id);
        return "redirect:/customers";
    }
}
