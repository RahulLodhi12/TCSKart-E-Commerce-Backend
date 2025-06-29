package com.tcsKart.CartService.bean;

import com.tcsKart.UserService.bean.Customer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cart_id;

    @OneToOne
    @JoinColumn(name = "customer_email") //This creates a column "customer_email" foreign key ref to "customer" table
    private Customer customer;

    @OneToMany(mappedBy = "cart") // One Cart has Many Cart Products; maps cart_id (P.K.) in "cart" table to cart_id (F.K.) in "cart_products" table
    private List<CartProducts> cartProductsList = new ArrayList<>();

    public Cart(){

    }

    public Cart(Integer cart_id, Customer customer, List<CartProducts> cartProductsList) {
        this.cart_id = cart_id;
        this.customer = customer;
        this.cartProductsList = cartProductsList;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartProducts> getCartProductsList() {
        return cartProductsList;
    }

    public void setCartProductsList(List<CartProducts> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }
}
/*
mysql> desc cart;
+----------------+--------------+------+-----+---------+----------------+
| Field          | Type         | Null | Key | Default | Extra          |
+----------------+--------------+------+-----+---------+----------------+
| cart_id        | int          | NO   | PRI | NULL    | auto_increment |
| customer_email | varchar(255) | YES  | UNI | NULL    |                |
+----------------+--------------+------+-----+---------+----------------+

✅ Keys in cart table:
Primary Key: cart_id
Unique Key: customer_email (enforces one-to-one relation with a customer)

*/