package com.tcsKart.ProductService.repository;

import com.tcsKart.ProductService.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.productName like %?1%") //need to use Java format only -> variable names
    List<Product> findByProductName(String name);

    List<Product> findByCategory(String category);
}
