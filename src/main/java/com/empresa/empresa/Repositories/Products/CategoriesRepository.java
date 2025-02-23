package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
