package com.empresa.empresa.Models.Products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dbo.categories")
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idCategory;
    private String categoryName;
    private Boolean isActive;

    //Image Category
    private String categoryImage;
}
