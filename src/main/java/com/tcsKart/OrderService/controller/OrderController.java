package com.tcsKart.OrderService.controller;

import com.tcsKart.OrderService.bean.OrderHistory;
import com.tcsKart.OrderService.bean.Orders;
import com.tcsKart.OrderService.repository.OrderHistoryRepo;
import com.tcsKart.OrderService.repository.OrderProductsRepo;
import com.tcsKart.OrderService.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderProductsRepo orderProductsRepo;

    @Autowired
    OrderHistoryRepo orderHistoryRepo;

    @GetMapping("/customer/{customerEmail}/orderHistory")
    public List<OrderHistory> getOrderHistoryByCustomerEmail(@PathVariable String customerEmail){
        List<Orders> ordersList = orderRepo.findByCustomerCustomerEmail(customerEmail);
        if(ordersList.isEmpty()){
            System.out.println("Fetch error..1");
            return null;
        }

        return orderHistoryRepo.findByOrdersCustomerCustomerEmail(customerEmail);
    }

    @GetMapping("/admin/allOrders")
    public List<Orders> getAllOrders(){
        return orderRepo.findAll();
    }

    @GetMapping("/customer/{customerEmail}/orderStatus")
    public List<Orders> checkOrderStatus(@PathVariable String customerEmail){
        List<Orders> ordersList = orderRepo.findByCustomerCustomerEmail(customerEmail);
        if(ordersList.isEmpty()){
            System.out.println("Fetch error..1");
            return null;
        }

        return ordersList;
    }

}
