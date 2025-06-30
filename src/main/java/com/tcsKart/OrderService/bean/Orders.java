package com.tcsKart.OrderService.bean;

import com.tcsKart.UserService.bean.Customer;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_email")
    private Customer customer;

    private String status;

    private Double amount;

    @CreationTimestamp
    private Date date;

    @OneToMany(mappedBy = "orders")
    List<OrderProducts> orderProductsList = new ArrayList<>();

    public Orders(){

    }

    public Orders(Integer orderId, Customer customer, String status, Double amount, Date date, List<OrderProducts> orderProductsList) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
        this.amount = amount;
        this.date = date;
        this.orderProductsList = orderProductsList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderProducts> getOrderProductsList() {
        return orderProductsList;
    }

    public void setOrderProductsList(List<OrderProducts> orderProductsList) {
        this.orderProductsList = orderProductsList;
    }
}
