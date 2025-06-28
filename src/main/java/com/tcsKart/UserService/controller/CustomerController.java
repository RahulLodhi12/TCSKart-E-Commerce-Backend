package com.tcsKart.UserService.controller;

import com.tcsKart.UserService.bean.Customer;
import com.tcsKart.UserService.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/admin/customers")
    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    @PostMapping("/register")
    public void registration(@RequestBody Customer customer){
        customerRepo.save(customer);
    }

//    @PostMapping("/login")
//    public void login()
}
