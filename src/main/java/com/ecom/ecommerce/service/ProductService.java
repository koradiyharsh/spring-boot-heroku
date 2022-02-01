package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

    public  void addProduct(Product product){
        this.productRepository.save(product);
    }

    public void removeProductById(Long id){
        this.productRepository.deleteById(id);
    }
    public Optional<Product> getProduct(Long id){
      return this.productRepository.findById(id);
    }
    public List<Product> getAllProductByCategoryId(Integer id){
        return this.productRepository.findAllByCategory_id(id);
    }
    public void deleteProductBy(Long id){
        this.productRepository.deleteById(id);
    }

}
