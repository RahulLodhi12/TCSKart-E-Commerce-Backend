package com.tcsKart.OrderService.repository;

import com.tcsKart.OrderService.bean.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
}
