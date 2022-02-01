package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.Category;
import com.ecom.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return this.categoryRepository.findAll();
    }

    public void addCatgory(Category category){
        this.categoryRepository.save(category);
    }

    public void deleteCategory(Integer id){
        this.categoryRepository.deleteById(id);
    }
    public Optional<Category> getCategory(Integer id){
        return this.categoryRepository.findById(id);
    }

}
