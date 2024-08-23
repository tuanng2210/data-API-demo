package com.example.demo_data_API.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo_data_API.domain.Customer;
/**
 * Repository interface for Customer entities.
 * This interface provides CRUD operations for Customer objects,
 * leveraging Spring Data JPA's built-in methods.
 */
public interface CustomersRepository extends CrudRepository <Customer, Long> {

}
