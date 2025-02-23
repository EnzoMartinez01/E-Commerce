package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.SubCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategories, Integer> {
}
