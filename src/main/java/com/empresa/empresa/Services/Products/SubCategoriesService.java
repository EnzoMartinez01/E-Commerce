package com.empresa.empresa.Services.Products;

import com.empresa.empresa.Models.Products.SubCategories;
import com.empresa.empresa.Repositories.Products.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubCategoriesService {
    private final static Logger logger = LoggerFactory.getLogger(SubCategoriesService.class);

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoriesService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    // Get all SubCategories
    public Page<SubCategories> getAllSubCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subCategoryRepository.findAll(pageable);
    }

    // Get SubCategory by ID
    public SubCategories getSubCategoryById(Integer id) {
        return subCategoryRepository.findById(id).orElse(null);
    }

    // Add SubCategory
    public SubCategories addSubCategory(SubCategories subCategory) {
        subCategory.setIsActive(true);
        return subCategoryRepository.save(subCategory);
    }

    // Update SubCategory
    public SubCategories updateSubCategory(Integer idSubCategory, SubCategories updatedSubCategory) {
        SubCategories existingSubCategory = subCategoryRepository.findById(idSubCategory)
                .orElseThrow(() -> new RuntimeException("SubCategory not found with ID: " + idSubCategory));

        existingSubCategory.setSubCategoryName(updatedSubCategory.getSubCategoryName() != null ? updatedSubCategory.getSubCategoryName() : existingSubCategory.getSubCategoryName());
        existingSubCategory.setIsActive(updatedSubCategory.getIsActive() != null ? updatedSubCategory.getIsActive() : existingSubCategory.getIsActive());

        return subCategoryRepository.save(existingSubCategory);
    }


    // Desactivate SubCategory
    public void deactivateSubCategory(Integer idSubCategory) {
        SubCategories subCategory = subCategoryRepository.findById(idSubCategory)
                .orElseThrow(() -> new RuntimeException("SubCategory not found with ID: " + idSubCategory));

        if (!subCategory.getIsActive()) {
            throw new IllegalStateException("SubCategory is already deactivated.");
        }

        subCategory.setIsActive(false);
        subCategoryRepository.save(subCategory);
    }
}
