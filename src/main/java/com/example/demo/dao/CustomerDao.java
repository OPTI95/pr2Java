package com.example.demo.dao;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDao {

    private static Long CUSTOMER_COUNT = 0L;
    private List<Customer> customers;

    {
        customers = new ArrayList<>();

        customers.add(new Customer(++CUSTOMER_COUNT, "Alice", "Johnson", "alice@example.com"));
        customers.add(new Customer(++CUSTOMER_COUNT, "Bob", "Smith", "bob@example.com"));
    }

    public List<Customer> index() {
        return customers;
    }

    public Customer show(Long id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findAny().orElse(null);
    }

    public void save(Customer customer) {
        customer.setId(++CUSTOMER_COUNT);
        customers.add(customer);
    }

    public void update(Long id, Customer updatedCustomer) {
        Customer existingCustomer = show(id);
        if (existingCustomer != null) {
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }
    }

    public void delete(Long id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }
}
