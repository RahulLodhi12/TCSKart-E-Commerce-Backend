package com.tcsKart.UserService.controller;

import com.tcsKart.OrderService.bean.OrderHistory;
import com.tcsKart.OrderService.bean.OrderProducts;
import com.tcsKart.OrderService.repository.OrderHistoryRepo;
import com.tcsKart.OrderService.repository.OrderProductsRepo;
import com.tcsKart.UserService.model.JwtRequest;
import com.tcsKart.UserService.model.JwtResponse;
import com.tcsKart.UserService.service.AppUserDetailsService;
import com.tcsKart.UserService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    OrderHistoryRepo orderHistoryRepo;

    @Autowired
    OrderProductsRepo orderProductsRepo;

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

    @GetMapping("/admin/statistics")
    public List<String> getSalesStatistics(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end){
        // Convert end date to 23:59:59.999 of the same day
        Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date adjustedEnd = cal.getTime();

        List<String> list = new ArrayList<>();

        List<OrderHistory> orderHistories = orderHistoryRepo.findAll();

        Double totalSales=0.0;
        Integer totalOrders=0;
        String products="";
        Integer maxQty=Integer.MIN_VALUE;
        for(OrderHistory oh: orderHistories){
            if(!oh.getDate().before(start) && !oh.getDate().after(adjustedEnd)){
                totalSales+=oh.getAmount();
                totalOrders++;
                List<OrderProducts> opList = orderProductsRepo.findByOrders_OrderId(oh.getOrders().getOrderId());
                for(OrderProducts op: opList){
                    if(op.getQuantity()>=maxQty){
                        maxQty=op.getQuantity();
                        products += op.getProduct().getProductName() + ", ";
                    }
                }
            }
        }

        list.add("Total Sales: "+totalSales.toString());
        list.add("Total Orders: "+totalOrders.toString());
        list.add("Top Selling Products: "+products);

        return list;
    }
}
