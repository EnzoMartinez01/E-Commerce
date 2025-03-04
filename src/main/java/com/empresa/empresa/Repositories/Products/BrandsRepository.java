package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.Brands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Integer> {
    List<Brands> findAllByIsActive(Boolean isActive);
    Page<Brands> findAllByIsActive(Pageable pageable, Boolean isActive);
}
