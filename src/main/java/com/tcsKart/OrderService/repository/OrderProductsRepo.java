package com.tcsKart.OrderService.repository;

import com.tcsKart.OrderService.bean.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepo extends JpaRepository<OrderProducts,Integer> {
}
