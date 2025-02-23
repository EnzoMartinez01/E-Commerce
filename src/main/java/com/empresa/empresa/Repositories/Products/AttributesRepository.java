package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.Attributes;
import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Models.Products.SubCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributesRepository extends JpaRepository<Attributes, Integer> {
    Page<Attributes> findByProducts(Pageable pageable, Products products);
    Page<Attributes> findBySubCategories(Pageable pageable, SubCategories subCategories);
}
