package com.empresa.empresa.Models.Products;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "dbo.subcategories")
@Data
public class SubCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubCategory;
    private String subCategoryName;
    private Boolean isActive;

    @OneToMany(mappedBy = "subCategories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Attributes> attributes;
}
