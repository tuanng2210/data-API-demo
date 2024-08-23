package com.example.demo_data_API.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo_data_API.domain.Customer;
import com.example.demo_data_API.repository.CustomersRepository;

/**
 * REST controller for managing customer-related API endpoints.
 * Provides operations to create, retrieve, update, and delete customers.
 */
@RestController
@RequestMapping("/api")
public class CustomerAPI {

    @Autowired
    CustomersRepository repo;

    /**
     * Retrieves all customers from the database.
     * @return an iterable collection of customers.
     */
    @GetMapping("/customers")
    public Iterable<Customer> getAll() {
        return repo.findAll();
    }

    /**
     * Retrieves a specific customer by their ID.
     * @param id the ID of the customer to retrieve.
     * @return an Optional containing the customer if found, or empty if not found.
     */
    @GetMapping("/customers/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable("customerId") Long id) {
        return repo.findById(id);
    }

    /**
     * Adds a new customer to the database.
     * The new customer must not have an ID and must have both a name and an email.
     * @param newCustomer the customer to add.
     * @param uri the UriComponentsBuilder to build the location URI.
     * @return a ResponseEntity with the status of the request.
     */
    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) {
        if (newCustomer.getId() != 0 || newCustomer.getName() == null || newCustomer.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        newCustomer = repo.save(newCustomer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newCustomer.getId()).toUri();
        ResponseEntity<?> response = ResponseEntity.created(location).build();
        return response;
    }

    /**
     * Updates an existing customer in the database.
     * The ID in the customer data must match the ID in the path.
     * The customer must have both a name and an email.
     * @param newCustomer the customer data to update.
     * @param customerId the ID of the customer to update.
     * @return a ResponseEntity with the status of the request.
     */
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> putCustomer(
            @RequestBody Customer newCustomer,
            @PathVariable("customerId") long customerId) {
        if (newCustomer.getId() != customerId || newCustomer.getName() == null ||
                newCustomer.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        newCustomer = repo.save(newCustomer);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a specific customer by their ID.
     * @param id the ID of the customer to delete.
     * @return a ResponseEntity with the status of the request.
     */
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long id) {

        repo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
