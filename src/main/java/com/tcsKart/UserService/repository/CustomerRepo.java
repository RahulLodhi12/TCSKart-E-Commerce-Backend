package com.tcsKart.UserService.repository;

import com.tcsKart.UserService.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    Optional<Customer> findByCustomerEmail(String customerEmail);
}
