package com.tcsKart.ProductService.controller;

import com.tcsKart.ProductService.bean.Product;
import com.tcsKart.ProductService.bean.Review;
import com.tcsKart.ProductService.repository.ProductRepo;
import com.tcsKart.ProductService.repository.ReviewRepo;
import com.tcsKart.UserService.bean.Customer;
import com.tcsKart.UserService.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("/product")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @GetMapping("/product/name/{name}")
    public List<Product> getProductByName(@PathVariable String name){
        return productRepo.findByProductName(name);
    }

    @GetMapping("/product/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category){
        return productRepo.findByCategory(category);
    }

    @PostMapping("/admin/product")
    public void addProduct(@RequestBody Product product){
        productRepo.save(product);
    }

    @PutMapping("/admin/product/{productId}")
    public void updateProductByPId(@PathVariable Integer productId, @RequestBody Product product){
        product.setProductId(productId);
        productRepo.save(product);
    }

    @DeleteMapping("/admin/product/{pId}")
    public void deleteProductByPId(@PathVariable Integer pId){
        productRepo.deleteById(pId);
    }

    @PostMapping("/customer/review/{customerEmail}/{pId}")
    public void addReview(@PathVariable String customerEmail, @PathVariable Integer pId, @RequestParam String text, @RequestParam Double rating){
        Review review = new Review();
        Optional<Customer> customer = customerRepo.findByCustomerEmail(customerEmail);
        Optional<Product> product = productRepo.findById(pId);
        System.out.println(product.get().getProductName());
        System.out.println(customer.get().getCustomerEmail());
        review.setCustomer(customer.get());
        review.setProduct(product.get());
        review.setReviewText(text);
        review.setRating(rating);
        reviewRepo.save(review);
    }

}
