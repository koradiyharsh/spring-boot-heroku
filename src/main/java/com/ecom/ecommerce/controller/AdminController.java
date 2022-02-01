package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.dto.ProductDTO;
import com.ecom.ecommerce.model.Category;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.service.CategoryService;
import com.ecom.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    static final String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories" , this.categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCategoryAdd(Model model){
        model.addAttribute("category" , new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCategoryAdd(@ModelAttribute("category") Category category)
    {
        this.categoryService.addCatgory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public  String deleteCategory(@PathVariable Integer id)
    {
        this.categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable Integer id , Model model){

        Optional<Category> category =  categoryService.getCategory(id);
        if(category.isPresent()){
            model.addAttribute("category" , category.get());
            return "categoriesAdd";

        }else{
            return "404";
        }
    }

    @GetMapping("/admin/products")
    public String getAllProduct(Model model){

        model.addAttribute("products" , this.productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model){

        model.addAttribute("productDTO" , new ProductDTO());
        model.addAttribute("categories" , this.categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String ProductAdd(@ModelAttribute("productDTO ") ProductDTO productDTO , @RequestParam("productImage") MultipartFile file
     , @RequestParam("imgName") String imageName ) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setCategory(this.categoryService.getCategory(productDTO.getCategoryId()).get());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setName(productDTO.getName());
        String imageUUID;
        if(!file.isEmpty())
        {
            imageUUID =  file.getOriginalFilename();
            Path findNameAndPath = Paths.get(uploadDir , imageUUID);
            Files.write(findNameAndPath , file.getBytes());
        }
        else{
            imageUUID = imageName;
        }
        product.setImageName(imageUUID);
        this.productService.addProduct(product);
        return "redirect:/admin/products";

    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        this.productService.deleteProductBy(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") Long id , Model model){

        Optional<Product> productget = this.productService.getProduct(id);
        ProductDTO productDTO  =  new ProductDTO();
        productDTO.setId(productget.get().getId());
        productDTO.setName(productget.get().getName());
        productDTO.setDescription(productget.get().getDescription());
        productDTO.setPrice(productget.get().getPrice());
        productDTO.setWeight(productget.get().getWeight());
        productDTO.setImageName(productget.get().getImageName());
        model.addAttribute("productDTO" , productDTO);
        model.addAttribute("categories" , this.categoryService.getAllCategory());
        return "productsAdd";

    }



}
