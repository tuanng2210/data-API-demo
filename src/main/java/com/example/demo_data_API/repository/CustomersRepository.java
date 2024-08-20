package com.example.demo_data_API.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo_data_API.domain.Customer;

public interface CustomersRepository extends CrudRepository <Customer, Long> {

}
