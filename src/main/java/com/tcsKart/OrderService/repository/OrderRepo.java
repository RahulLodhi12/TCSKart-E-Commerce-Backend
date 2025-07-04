package com.tcsKart.OrderService.repository;

import com.tcsKart.OrderService.bean.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
    List<Orders> findByCustomerCustomerEmail(String customerEmail);
}
