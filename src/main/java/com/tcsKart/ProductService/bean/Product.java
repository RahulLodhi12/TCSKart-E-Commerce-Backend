package com.tcsKart.ProductService.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private Double price;
    private String description;
    private Double rating;
    private String category;
    private Integer quantity;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Review> reviewList = new ArrayList<>();

    public Product(){

    }

    public Product(Integer productId, String productName, Double price, String description, Double rating, String category, Integer quantity, List<Review> reviewList) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.quantity = quantity;
        this.reviewList = reviewList;
    }

    public Product(Integer productId, String productName, Double price, String description, Double rating, String category, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
