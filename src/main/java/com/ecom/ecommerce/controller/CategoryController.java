package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.entities.Category;
import com.ecom.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/ecommerce/addCategory")
    public Category saveCategory(@RequestBody Category category)
    {
        return categoryService.save(category);
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @GetMapping("/ecommerce/getCategory")
    public List<Category> getAllCategories()
    {
        return categoryService.getALLCategories();

    }

}

