package com.ecom.ecommerce.service;

import com.ecom.ecommerce.dao.CategoryDao;
import com.ecom.ecommerce.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public Category save(Category category) {
        return this.categoryDao.save(category);
    }

    public List<Category> getALLCategories() {
        return this.categoryDao.findAll();
    }
}
