package com.tcsKart.OrderService.repository;

import com.tcsKart.OrderService.bean.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductsRepo extends JpaRepository<OrderProducts,Integer> {

    List<OrderProducts> findByOrdersOrderId(int orderId);
}
