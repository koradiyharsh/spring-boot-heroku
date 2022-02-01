package com.ecom.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name  = "category")
@Data
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "category_id")
        private Integer id;

        @Column(name = "category_name")
        private String name;

}
