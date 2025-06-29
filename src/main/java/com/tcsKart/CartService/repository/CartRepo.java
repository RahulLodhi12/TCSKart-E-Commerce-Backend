package com.tcsKart.CartService.repository;

import com.tcsKart.CartService.bean.Cart;
import com.tcsKart.UserService.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByCustomer(Customer customer);
}
