package com.example.demo_data_API.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_data_API.domain.Customer;
import com.example.demo_data_API.repository.CustomersRepository;

@RestController
@RequestMapping("/api")
public class CustomerAPI {
    private List<Customer> customers;
    CustomersRepository repo;

    @Autowired
    public CustomerAPI() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "John Doe", "johndoe@example.com"));
        customers.add(new Customer(2, "Jane Smith", "janesmith@example.com"));
        customers.add(new Customer(3, "Alice Johnson", "alicej@example.com"));
    }

    @GetMapping("/customers")
    public Iterable<Customer> getAll() {
        return customers;
    }

    // @GetMapping("/customers/{id}")
    // public Optional<Customer> getCustomerById(@PathVariable Long id) {
    //     return repo.findById(id);
    // }

    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        // Search for the customer by ID in the hardcoded list
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst();
    }

}
