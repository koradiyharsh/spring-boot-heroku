package com.ecom.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double price;
    private double weight;
    private String description;
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id" , referencedColumnName = "category_id")
    public Category category;
}
