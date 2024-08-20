package com.example.demo_data_API.api;

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

    @Autowired
    CustomersRepository repo;

    @GetMapping("/customers")
    public Iterable<Customer> getAll() {
        return repo.findAll();
    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") Long id) {
        return repo.findById(id);
    }

}
