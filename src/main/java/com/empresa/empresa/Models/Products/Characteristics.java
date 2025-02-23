package com.empresa.empresa.Models.Products;

import com.empresa.empresa.Deserializer.Products.ProductsDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dbo_characteristics")
@Data
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products")
    @JsonDeserialize(using = ProductsDeserializer.class)
    @JsonBackReference(value = "characteristics-product")
    private Products products;

}
