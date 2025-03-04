package com.empresa.empresa.Repositories.Products;

import com.empresa.empresa.Models.Products.SubCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategories, Integer> {
    @Query("SELECT DISTINCT s FROM Products p " +
            "JOIN p.attributes a " +
            "JOIN a.subCategories s " +
            "WHERE (:searchTerms IS NULL OR " +
            "      LOWER(p.productName) LIKE LOWER(CONCAT('%', :searchTerms, '%')) OR " +
            "      LOWER(p.category.categoryName) LIKE LOWER(CONCAT('%', :searchTerms, '%')) OR " +
            "      LOWER(p.brand.brandName) LIKE LOWER(CONCAT('%', :searchTerms, '%'))) " +
            "AND (:price IS NULL OR p.price = :price) " +
            "AND (:stock IS NULL OR p.stock = :stock) " +
            "AND (:isOffer IS NULL OR p.isOffer = :isOffer) " +
            "AND (:brandId IS NULL OR p.brand.idBrand = :brandId) " +
            "AND (:categoryId IS NULL OR p.category.idCategory = :categoryId) " +
            "AND (:attributeIds IS NULL OR a.id IN :attributeIds) " +
            "AND (COALESCE(:isActive, true) = p.isActive)")
    List<SubCategories> findAvailableSubCategories(@Param("searchTerms") String searchTerms,
                                                   @Param("price") Double price,
                                                   @Param("stock") Integer stock,
                                                   @Param("isOffer") Boolean isOffer,
                                                   @Param("brandId") Integer brandId,
                                                   @Param("categoryId") Integer categoryId,
                                                   @Param("attributeIds") List<Integer> attributeIds,
                                                   @Param("isActive") Boolean isActive);
    Page<SubCategories> findAllByIsActive(Boolean isActive, Pageable pageable);
}
