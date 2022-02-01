package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product , Long> {

    List<Product> findAllByCategory_id(Integer id);
}