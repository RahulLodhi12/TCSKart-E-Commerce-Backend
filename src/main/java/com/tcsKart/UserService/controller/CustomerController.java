package com.tcsKart.UserService.controller;

import com.tcsKart.UserService.bean.Customer;
import com.tcsKart.UserService.repository.CustomerRepo;
import com.tcsKart.UserService.service.CustomerDetailsService;
import com.tcsKart.UserService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/admin/customer")
    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    @PostMapping("/register")
    public void registration(@RequestBody Customer customer){
        customerRepo.save(customer);
    }

//    @PostMapping("/customer/login")
//    public String createAuthToken(@RequestBody Customer customer) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(customer.getCustomerEmail(), customer.getCustomerPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        final UserDetails userDetails = customerDetailsService
//                .loadUserByUsername(customer.getCustomerEmail());
//
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//        return jwt;
//    }

    @GetMapping("customer/{customerEmail}")
    public Optional<Customer> getCustomerDetailsByEmail(@PathVariable String customerEmail, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){

        String customerEmailId = usernamePasswordAuthenticationToken.getName();

        Optional<Customer> customer = customerRepo.findByCustomerEmail(customerEmailId);

        if(!customer.get().getCustomerEmail().equals(customerEmail)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Only Access Own Details..");
        }

        return customer;
    }
}
