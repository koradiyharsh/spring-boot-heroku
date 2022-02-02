package com.ecom.ecommerce.entities;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ecom_category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer cat_id;

    @Column(name = "cat_name")
    private String cat_name;

}