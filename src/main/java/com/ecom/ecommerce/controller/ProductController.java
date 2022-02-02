package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.entities.Product;
import com.ecom.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/ecommerce/getProducts/{id}")
    List<Product> findProduct(@PathVariable Integer id) {
        return productService.findProduct(id);
    }

    @GetMapping("/ecommerce/getProduct/{id}")
    public Optional<Product> findByProductId(@PathVariable Integer id) {
        return productService.findProductById(id);
    }

    @PostMapping("/ecommerce/saveProduct")
    public Product saveProduct(@RequestBody Product product) {

        return productService.saveProduct(product);
    }

    @GetMapping("/ecommerce/getProduct")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}
