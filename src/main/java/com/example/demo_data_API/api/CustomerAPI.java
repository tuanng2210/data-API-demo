package com.example.demo_data_API.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) {
		if (newCustomer.getId() != 0 || newCustomer.getName() == null || newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer = repo.save(newCustomer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/customers/{id}")
				.buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}

}
