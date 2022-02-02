package com.ecom.ecommerce.dao;

import com.ecom.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.category.cat_id IN (:cat_id)")
    List<Product> findProduct(@Param("cat_id") Integer cat_id);

}
