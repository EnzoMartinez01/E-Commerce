package com.empresa.empresa.Models.Products;

import com.empresa.empresa.Deserializer.Products.BrandsDeserializer;
import com.empresa.empresa.Deserializer.Products.CategoriesDeserializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name= "dbo_products")
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    @Column(length = 1000)
    private String productDescription;
    private String sku;
    private Double price;
    private Double price_creditcard;
    private Integer quantity;
    private String product_image;
    private Integer stock;
    private Integer offerDescount;
    private Double priceOffer;
    private String pdfFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonDeserialize(using = BrandsDeserializer.class)
    private Brands brand;

    private Boolean isOffer;
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonDeserialize(using = CategoriesDeserializer.class)
    private Categories category;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "attributes-product")
    private List<Attributes> attributes;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "characteristics-product")
    private List<Characteristics> characteristics;

}