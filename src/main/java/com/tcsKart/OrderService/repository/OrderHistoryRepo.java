package com.tcsKart.OrderService.repository;

import com.tcsKart.OrderService.bean.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepo extends JpaRepository<OrderHistory,Integer> {
    List<OrderHistory> findByOrdersCustomerCustomerEmail(String customerEmail);
}
