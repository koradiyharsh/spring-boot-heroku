package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.service.CategoryService;
import com.ecom.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/"  , "/home"})
    public String home(Model model){
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model){

        model.addAttribute("categories" ,this.categoryService.getAllCategory());
        model.addAttribute("products" , this.productService.getAllProduct());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String getProductsByCategory(@PathVariable Integer id , Model model){

        model.addAttribute("categories" , this.categoryService.getAllCategory());
        model.addAttribute("products" ,this.productService.getAllProductByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductBy(@PathVariable Long id , Model model){

        model.addAttribute("product"  , this.productService.getProduct(id).get());
        return "viewProduct";
    }

}

