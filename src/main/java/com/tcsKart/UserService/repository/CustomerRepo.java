package com.tcsKart.UserService.repository;

import com.tcsKart.UserService.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,String> {
}
