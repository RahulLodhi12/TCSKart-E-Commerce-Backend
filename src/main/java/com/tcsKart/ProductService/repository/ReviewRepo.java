package com.tcsKart.ProductService.repository;

import com.tcsKart.ProductService.bean.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review,Integer> {

}
