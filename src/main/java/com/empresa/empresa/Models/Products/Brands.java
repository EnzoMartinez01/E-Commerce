package com.empresa.empresa.Models.Products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dbo.brands")
@Data
public class Brands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBrand;

    private String brandName;
    private Boolean isActive;

    //Image Brand
    private String brandImage;
}
