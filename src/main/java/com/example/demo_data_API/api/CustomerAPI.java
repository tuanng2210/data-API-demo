package com.example.demo_data_API.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_data_API.domain.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {
    private List<Customer> customers;
    // CustomersRepository repo;

    @Autowired
    public CustomerAPI() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "John Doe", "johndoe@example.com"));
        customers.add(new Customer(2, "Jane Smith", "janesmith@example.com"));
        customers.add(new Customer(3, "Alice Johnson", "alicej@example.com"));
    }

    @GetMapping
    public Iterable<Customer> getAll() {
        return customers;
    }

}
