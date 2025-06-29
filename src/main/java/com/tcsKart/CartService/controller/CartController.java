package com.tcsKart.CartService.controller;

import com.tcsKart.CartService.bean.Cart;
import com.tcsKart.CartService.bean.CartProducts;
import com.tcsKart.CartService.repository.CartProductsRepo;
import com.tcsKart.CartService.repository.CartRepo;
import com.tcsKart.ProductService.bean.Product;
import com.tcsKart.ProductService.repository.ProductRepo;
import com.tcsKart.UserService.bean.Customer;
import com.tcsKart.UserService.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/customer/{customerEmail}/addCart")
    public void addToCart(@PathVariable String customerEmail, @RequestParam Integer productId, @RequestParam Integer quantity) throws ClassNotFoundException {
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

        //pending
        CartProducts cartProducts = new CartProducts();
        cartProducts.setCart(cart.get());
        cartProducts.setProduct(product.get());
        cartProducts.setQuantity(quantity);
        cartProductsRepo.save(cartProducts);
    }
}
