package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Integer> {
}
