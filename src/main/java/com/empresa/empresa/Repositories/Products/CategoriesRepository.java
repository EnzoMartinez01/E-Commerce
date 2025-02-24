package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    Optional<Categories> findByCategoryName(String name);
}
