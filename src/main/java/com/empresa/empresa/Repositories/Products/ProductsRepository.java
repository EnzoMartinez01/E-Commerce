package com.empresa.empresa.Repositories.Products;
import com.empresa.empresa.Models.Products.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT DISTINCT p FROM Products p " +
            "LEFT JOIN p.attributes a " +
            "LEFT JOIN a.subCategories s " +
            "WHERE (:searchTerms IS NULL OR " +
            "      LOWER(p.productName) LIKE LOWER(CONCAT('%', :searchTerms, '%')) OR " +
            "      LOWER(p.category.categoryName) LIKE LOWER(CONCAT('%', :searchTerms, '%')) OR " +
            "      LOWER(p.brand.brandName) LIKE LOWER(CONCAT('%', :searchTerms, '%'))) " +
            "AND (:price IS NULL OR p.price = :price) " +
            "AND (:stock IS NULL OR p.stock = :stock) " +
            "AND (:isOffer IS NULL OR p.isOffer = :isOffer) " +
            "AND (:brandId IS NULL OR p.brand.idBrand = :brandId) " +
            "AND (:categoryId IS NULL OR p.category.idCategory = :categoryId) " +
            "AND (:subCategoryId IS NULL OR (s.idSubCategory = :subCategoryId AND s IS NOT NULL)) " +
            "AND (:attributeIds IS NULL OR a.id IN :attributeIds OR a IS NULL) " +
            "AND (COALESCE(:isActive, true) = p.isActive)")
    Page<Products> findByFilters(@Param("searchTerms") String searchTerms,
                                 @Param("price") Double price,
                                 @Param("stock") Integer stock,
                                 @Param("isOffer") Boolean isOffer,
                                 @Param("brandId") Integer brandId,
                                 @Param("categoryId") Integer categoryId,
                                 @Param("subCategoryId") Integer subCategoryId,
                                 @Param("attributeIds") List<Integer> attributeIds,
                                 @Param("isActive") Boolean isActive,
                                 Pageable pageable);


}
