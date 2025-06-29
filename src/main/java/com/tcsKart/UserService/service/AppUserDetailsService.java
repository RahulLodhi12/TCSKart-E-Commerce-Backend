package com.tcsKart.UserService.service;

import com.tcsKart.UserService.bean.Admin;
import com.tcsKart.UserService.bean.Customer;
import com.tcsKart.UserService.repository.AdminRepo;
import com.tcsKart.UserService.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First try admin
        Optional<Admin> adminOpt = adminRepo.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return new User(admin.getUsername(), admin.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(admin.getRole())));
        }

        // Then try customer
        Optional<Customer> custOpt = customerRepo.findByCustomerEmail(username);
        if (custOpt.isPresent()) {
            Customer customer = custOpt.get();
            return new User(customer.getCustomerEmail(), customer.getCustomerPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(customer.getRole())));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
