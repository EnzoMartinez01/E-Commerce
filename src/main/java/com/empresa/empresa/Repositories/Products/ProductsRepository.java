package com.empresa.empresa.Repositories.Products;
import com.empresa.empresa.Models.Products.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT p FROM Products p " +
            "WHERE (:productName IS NULL OR p.productName LIKE %:productName%) " +
            "AND (:price IS NULL OR p.price = :price) " +
            "AND (:stock IS NULL OR p.stock = :stock) " +
            "AND (:isOffer IS NULL OR p.isOffer = :isOffer) " +
            "AND (:brandId IS NULL OR p.brand.idBrand = :brandId) " +
            "AND (:categoryId IS NULL OR p.category.idCategory = :categoryId) " +
            "AND (:isActive IS NULL OR p.isActive = :isActive)")
    Page<Products> findByFilters(@Param("productName") String productName,
                                 @Param("price") Double price,
                                 @Param("stock") Integer stock,
                                 @Param("isOffer") Boolean isOffer,
                                 @Param("brandId") Integer brandId,
                                 @Param("categoryId") Integer categoryId,
                                 @Param("isActive") Boolean isActive,
                                 Pageable pageable);
}
