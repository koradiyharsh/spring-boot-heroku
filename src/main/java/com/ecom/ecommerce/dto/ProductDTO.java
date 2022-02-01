package com.ecom.ecommerce.dto;

import com.ecom.ecommerce.model.Category;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private double price;
    private double weight;
    private String description;
    private String imageName;
    private int categoryId;
}
