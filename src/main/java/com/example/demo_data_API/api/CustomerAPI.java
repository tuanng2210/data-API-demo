package com.example.demo_data_API.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_data_API.domain.Customer;
import com.example.demo_data_API.repository.CustomersRepository;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {
    @Autowired
    CustomersRepository repo;

    // @GetMapping
    // public Iterable<Customer> getAll() {
    //     return repo.findAll();
    // }

    @GetMapping("/customer1")
    public String Hello(){
        return "tuan";
    }
}
