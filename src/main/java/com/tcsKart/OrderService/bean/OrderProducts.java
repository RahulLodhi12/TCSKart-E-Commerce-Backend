package com.tcsKart.OrderService.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcsKart.ProductService.bean.Product;
import jakarta.persistence.*;

@Entity
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderProductId;

    @ManyToOne //make "product_id" of "OrderProducts" table Foreign Key
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne //make "order_id" of "OrderProducts" table Foreign Key
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders orders;

    private Integer quantity;


    public OrderProducts(){

    }

    public OrderProducts(Integer orderProductId, Product product, Orders orders, Integer quantity) {
        this.orderProductId = orderProductId;
        this.product = product;
        this.orders = orders;
        this.quantity = quantity;
    }

    public Integer getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
