package com.microcompany.microservices.usermanagemeentservice.repositories;

import com.microcompany.microservices.usermanagemeentservice.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
