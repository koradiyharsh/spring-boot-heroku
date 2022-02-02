package com.ecom.ecommerce.service;

import com.ecom.ecommerce.dao.ProductDao;
import com.ecom.ecommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<Product> findProduct(Integer catId) {
        return productDao.findProduct(catId);
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Optional<Product> findProductById(Integer id) {
        return productDao.findById(id);
    }

}
