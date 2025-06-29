package com.tcsKart.UserService.bean;

import com.tcsKart.CartService.bean.Cart;
import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
public class Customer {
    @Id
    private String customerEmail;
    private String customerPassword;
    private String customerName;
    private String address;
    private String pincode;
    private final String role="ROLE_CUSTOMER";

    @OneToOne(mappedBy = "customer") //This doesn't create any column in "customer" table.
    private Cart cart;

    public Customer(){

    }

    public Customer(String customerEmail, String customerPassword, String customerName, String address, String pincode) {
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerName = customerName;
        this.address = address;
        this.pincode = pincode;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getRole() {
        return role;
    }
}
