package com.tcsKart.UserService.service;

import com.tcsKart.UserService.bean.Admin;
import com.tcsKart.UserService.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return new User(admin.getUsername(), admin.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(admin.getRole())));
    }
}
