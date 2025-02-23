package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.Characteristics;
import com.empresa.empresa.Models.Products.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristics, Integer> {
    Page<Characteristics> findByProducts(Pageable pageable, Products products);
}
