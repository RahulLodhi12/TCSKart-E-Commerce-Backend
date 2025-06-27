package com.tcsKart.ProductService.controller;

import com.tcsKart.ProductService.bean.Product;
import com.tcsKart.ProductService.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepo productRepo;

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

    @PutMapping("/admin/product/{pId}")
    public void updateProductByPId(@PathVariable Integer pId, @RequestBody Product product){
        product.setpId(pId);
        productRepo.save(product);
    }

    @DeleteMapping("/admin/product/{pId}")
    public void deleteProductByPId(@PathVariable Integer pId){
        productRepo.deleteById(pId);
    }

}
