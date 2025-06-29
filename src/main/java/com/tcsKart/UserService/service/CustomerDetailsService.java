package com.tcsKart.UserService.service;

import com.tcsKart.UserService.bean.Customer;
import com.tcsKart.UserService.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Customer customer = customerRepo.findByCustomerEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        return new User(customer.getCustomerEmail(), customer.getCustomerPassword(),
                Collections.singleton(new SimpleGrantedAuthority(customer.getRole())));
    }
}
