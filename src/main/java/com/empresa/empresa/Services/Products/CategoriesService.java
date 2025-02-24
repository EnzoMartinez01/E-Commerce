package com.empresa.empresa.Services.Products;

import com.empresa.empresa.Models.Products.Categories;
import com.empresa.empresa.Repositories.Products.CategoriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {
    private final static Logger logger = LoggerFactory.getLogger(CategoriesService.class);

    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    // Get all categories
    public Page<Categories> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoriesRepository.findAll(pageable);
    }

    // Get category by ID
    public Categories getCategoryById(Integer id) {
        return categoriesRepository.findById(id).orElse(null);
    }

    // Get category By Name
    public Categories getCategoryByName(String name) {
        return categoriesRepository.findByCategoryName(name).orElse(null);
    }

    // Add category
    public Categories addCategory(Categories category) {
        category.setIsActive(true);
        return categoriesRepository.save(category);
    }

    // Update category
    public Categories updateCategory(Integer idCategory, Categories updatedCategory) {
        Categories existingCategory = categoriesRepository.findById(idCategory)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + idCategory));

        existingCategory.setCategoryName(updatedCategory.getCategoryName() != null ? updatedCategory.getCategoryName() : existingCategory.getCategoryName());
        existingCategory.setIsActive(updatedCategory.getIsActive() != null ? updatedCategory.getIsActive() : existingCategory.getIsActive());
        existingCategory.setCategoryImage(updatedCategory.getCategoryImage() != null ? updatedCategory.getCategoryImage() : existingCategory.getCategoryImage());

        return categoriesRepository.save(existingCategory);
    }

    // Deactivate category
    public void deactivateCategory(Integer idCategory) {
        Categories category = categoriesRepository.findById(idCategory)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + idCategory));

        if (!category.getIsActive()) {
            throw new IllegalStateException("Category is already deactivated.");
        }

        category.setIsActive(false);
        categoriesRepository.save(category);
    }

}
