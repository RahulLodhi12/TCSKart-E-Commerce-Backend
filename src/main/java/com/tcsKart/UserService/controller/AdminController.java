//package com.tcsKart.UserService.controller;
//
//import com.tcsKart.UserService.bean.Admin;
//import com.tcsKart.UserService.bean.Customer;
//import com.tcsKart.UserService.service.AdminDetailsService;
//import com.tcsKart.UserService.service.CustomerDetailsService;
//import com.tcsKart.UserService.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AdminController {
//    @Autowired
//    private AdminDetailsService adminDetailsService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @PostMapping("/admin/login")
//    public String loginAdmin(@RequestBody Admin admin) throws Exception {
////        try {
////            authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword())
////            );
////        }
////
//        try {
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                admin.getUsername(),
//                                admin.getPassword())
//                );
//
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        final UserDetails userDetails = adminDetailsService
//                .loadUserByUsername(admin.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//        return jwt;
//    }
//}
