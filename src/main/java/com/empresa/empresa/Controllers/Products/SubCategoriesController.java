package com.empresa.empresa.Controllers.Products;

import com.empresa.empresa.Models.Products.SubCategories;
import com.empresa.empresa.Services.Products.SubCategoriesService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subcategories")
public class SubCategoriesController {
    private final SubCategoriesService subCategoriesService;

    public SubCategoriesController(SubCategoriesService subCategoriesService){
        this.subCategoriesService = subCategoriesService;
    }

    @GetMapping("/getAllSubCategories")
    public Page<SubCategories> getAllSubCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return subCategoriesService.getAllSubCategories(page, size);
    }

    @GetMapping("/getSubCategory/{id}")
    public SubCategories getSubCategoryById(Integer id){
        return subCategoriesService.getSubCategoryById(id);
    }

    @PostMapping("/addSubCategory")
    public ResponseEntity<Map<String, String>> addSubCategory(SubCategories subCategory){
        SubCategories savedSubCategory = subCategoriesService.addSubCategory(subCategory);

        Map<String, String> response = new HashMap<>();
        response.put("message", "SubCategory created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/updateSubCategory/{idSubCategory}")
    public ResponseEntity<Map<String, String>> updateSubCategory(Integer idSubCategory, SubCategories updatedSubCategory){
        SubCategories subCategory = subCategoriesService.updateSubCategory(idSubCategory, updatedSubCategory);

        Map<String, String> response = new HashMap<>();
        response.put("message", "SubCategory updated successfully");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/deactivateSubCategory/{idSubCategory}")
    public ResponseEntity<Map<String, String>> deactivateSubCategory(Integer idSubCategory){
        subCategoriesService.deactivateSubCategory(idSubCategory);

        Map<String, String> response = new HashMap<>();
        response.put("message", "SubCategory deactivated successfully");
        return ResponseEntity.ok(response);
    }
}
