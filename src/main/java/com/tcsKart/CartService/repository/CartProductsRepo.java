package com.tcsKart.CartService.repository;

import com.tcsKart.CartService.bean.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CartProductsRepo extends JpaRepository<CartProducts,Integer> {
//    List<CartProducts> findAllByCartId(int cartId);

    List<CartProducts> findByCartCartId(int cartId);

    void deleteByCartCartId(int cartId);

    Optional<CartProducts> findByCartCartIdAndProductProductId(int cartId, Integer productId);
}
