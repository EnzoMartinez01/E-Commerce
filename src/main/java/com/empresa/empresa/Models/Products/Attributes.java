package com.empresa.empresa.Models.Products;

import com.empresa.empresa.Deserializer.Products.ProductsDeserializer;
import com.empresa.empresa.Deserializer.Products.SubCategoryDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dbo_attributes")
@Data
public class Attributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String attributeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory")
    @JsonDeserialize(using = SubCategoryDeserializer.class)
    @JsonBackReference
    private SubCategories subCategories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products")
    @JsonDeserialize(using = ProductsDeserializer.class)
    @JsonBackReference(value = "attributes-product")
    private Products products;

}
