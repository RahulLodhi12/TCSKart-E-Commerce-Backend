package com.tcsKart.CartService.repository;

import com.tcsKart.CartService.bean.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductsRepo extends JpaRepository<CartProducts,Integer> {
}
