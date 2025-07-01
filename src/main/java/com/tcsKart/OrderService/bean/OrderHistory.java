package com.tcsKart.OrderService.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderHistoryId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    private Date date;

    private Double amount;

    @OneToMany //single direction
    @JoinColumn(name = "order_id")
    private List<OrderProducts> orderProductsList;

    public OrderHistory(){

    }

    public OrderHistory(Integer orderHistoryId, Orders orders, Date date, Double amount, List<OrderProducts> orderProductsList) {
        this.orderHistoryId = orderHistoryId;
        this.orders = orders;
        this.date = date;
        this.amount = amount;
        this.orderProductsList = orderProductsList;
    }

    public Integer getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(Integer orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonIgnore
    public List<OrderProducts> getOrderProductsList() {
        return orderProductsList;
    }

    public void setOrderProductsList(List<OrderProducts> orderProductsList) {
        this.orderProductsList = orderProductsList;
    }
}
