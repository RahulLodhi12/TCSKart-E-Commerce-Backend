package com.tcsKart.CartService.controller;

import com.tcsKart.CartService.bean.Cart;
import com.tcsKart.CartService.bean.CartProducts;
import com.tcsKart.CartService.repository.CartProductsRepo;
import com.tcsKart.CartService.repository.CartRepo;
import com.tcsKart.OrderService.bean.OrderProducts;
import com.tcsKart.OrderService.bean.Orders;
import com.tcsKart.OrderService.repository.OrderProductsRepo;
import com.tcsKart.OrderService.repository.OrderRepo;
import com.tcsKart.ProductService.bean.Product;
import com.tcsKart.ProductService.repository.ProductRepo;
import com.tcsKart.UserService.bean.Customer;
import com.tcsKart.UserService.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CartController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartProductsRepo cartProductsRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderProductsRepo orderProductsRepo;

    @PostMapping("/customer/{customerEmail}/{productId}") //Need to take the CustomerEmail From JWT Token -> refer the CustomerController.java under "UserService" package
    public void addToCart(@PathVariable String customerEmail, @PathVariable Integer productId) throws ClassNotFoundException {
        Optional<Customer> customer = customerRepo.findByCustomerEmail(customerEmail);
        if(customer.isEmpty()) {
            return;
        }

        Optional<Product> product = productRepo.findById(productId);
        if(product.isEmpty()) {
            return;
        }

        Optional<Cart> cart = cartRepo.findByCustomer(customer.get());
        if(cart.isEmpty()){
            Cart newCart = new Cart();
            newCart.setCustomer(customer.get());
            cart = Optional.of(cartRepo.save(newCart));
        }

        CartProducts cartProducts = new CartProducts();
        cartProducts.setCart(cart.get());
        cartProducts.setProduct(product.get());
        cartProducts.setQuantity(1);
        cartProductsRepo.save(cartProducts);
    }

    @GetMapping("/customer/{customerEmail}/viewCart")
    public List<CartProducts> viewCart(@PathVariable String customerEmail){
        Optional<Customer> customer = customerRepo.findById(customerEmail);
        if(customer.isEmpty()){
            return null;
        }

        Optional<Cart> cart = cartRepo.findByCustomerCustomerEmail(customerEmail);
        if(cart.isEmpty()){
            return null;
        }

        int cartId = cart.get().getCartId();
        return cartProductsRepo.findByCartCartId(cartId);
    }

    @DeleteMapping("/customer/{customerEmail}") //remove complete cart
    public void deleteCart(@PathVariable String customerEmail){
        Optional<Customer> customer = customerRepo.findById(customerEmail);
        if(customer.isEmpty()){
            return;
        }

        Optional<Cart> cart = cartRepo.findByCustomerCustomerEmail(customerEmail);
        if(cart.isEmpty()){
            return;
        }

        int cartId = cart.get().getCartId();
        cartRepo.deleteById(cartId);
    }

    @DeleteMapping("/customer/{customerEmail}/{productId}") //remove single product from cart
    public void removeProductFromCart(@PathVariable String customerEmail, @PathVariable Integer productId){
        Optional<Customer> customer = customerRepo.findById(customerEmail);
        if(customer.isEmpty()){
            System.out.println("Not deleted..1");
            return;
        }

        Optional<Cart> cart = cartRepo.findByCustomerCustomerEmail(customerEmail);
        if(cart.isEmpty()){
            System.out.println("Not deleted..2");
            return;
        }

        int cartId = cart.get().getCartId();
        Optional<CartProducts> cartProducts = cartProductsRepo.findByCartCartIdAndProductProductId(cartId,productId);
        if(cartProducts.isEmpty()){
            System.out.println("Not deleted..3");
            return;
        }
        cartProductsRepo.deleteById(cartProducts.get().getCartProductId());
    }

    @PutMapping("/customer/{customerEmail}/{productId}")
    public void updateQuantity(@PathVariable String customerEmail, @PathVariable Integer productId){
        Optional<Customer> customer = customerRepo.findById(customerEmail);
        if(customer.isEmpty()){
            System.out.println("Not updated..1");
            return;
        }

        Optional<Cart> cart = cartRepo.findByCustomerCustomerEmail(customerEmail);
        if(cart.isEmpty()){
            System.out.println("Not updated..2");
            return;
        }

        int cartId = cart.get().getCartId();
        Optional<CartProducts> cartProducts = cartProductsRepo.findByCartCartIdAndProductProductId(cartId,productId);
        if(cartProducts.isEmpty()){
            System.out.println("Not updated..3");
            return;
        }

        int currentQty = cartProducts.get().getQuantity();
        cartProducts.get().setQuantity(currentQty+1);
        cartProductsRepo.save(cartProducts.get());
    }

    @PostMapping("customer/{customerEmail}")
    public void placeOrder(@PathVariable String customerEmail){
        Optional<Customer> customer = customerRepo.findById(customerEmail);
        if(customer.isEmpty()){
            System.out.println("Not inserted..1");
            return;
        }

        Optional<Cart> cart = cartRepo.findByCustomerCustomerEmail(customerEmail);
        if(cart.isEmpty()){
            System.out.println("Not inserted..2");
            return;
        }

        int cartId = cart.get().getCartId();
        List<CartProducts> cartProducts = cartProductsRepo.findByCartCartId(cartId);
        if(cartProducts.isEmpty()){
            System.out.println("Not inserted..3");
            return;
        }

        Orders orders = new Orders();
        List<OrderProducts> orderProductsList = new ArrayList<>();

        orders.setCustomer(customer.get());
        orders.setStatus("pending");

        Double amt=0.0;
        for(CartProducts cp: cartProducts){
            OrderProducts op = new OrderProducts();
            int pId = cp.getProduct().getProductId();
            Optional<Product> product = productRepo.findById(pId);
            int qty = cp.getQuantity();
            op.setQuantity(qty);
            op.setProduct(product.get());
            op.setOrders(orders);
            //Calculating Total Amount
            while(qty>0){
                amt+=product.get().getPrice();
                qty--;
            }
            orderProductsList.add(op);
        }
        orders.setAmount(amt);
        orders.setOrderProductsList(orderProductsList);
        orderRepo.save(orders);

        for(CartProducts cp: cartProducts){
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setOrders(orders);
            orderProducts.setProduct(cp.getProduct());
            orderProducts.setQuantity(cp.getQuantity());
            orderProductsRepo.save(orderProducts);
        }
    }
}
