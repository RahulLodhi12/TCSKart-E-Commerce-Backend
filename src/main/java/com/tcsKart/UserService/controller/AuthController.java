package com.tcsKart.UserService.controller;

import com.tcsKart.UserService.model.JwtRequest;
import com.tcsKart.UserService.model.JwtResponse;
import com.tcsKart.UserService.service.AppUserDetailsService;
import com.tcsKart.UserService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception {
        String username = req.getUsername();
        String password = req.getPassword();

        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        // Authenticate using username and password
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // Load user details (admin or customer)
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);

        // Generate JWT token
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
