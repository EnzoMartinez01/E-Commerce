package com.empresa.empresa.Controllers.Products;

import com.empresa.empresa.Models.Products.Categories;
import com.empresa.empresa.Services.Products.CategoriesService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/getAllCategories")
    public Page<Categories> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoriesService.getAllCategories(page, size);
    }

    @GetMapping("/getCategory/{id}")
    public Categories getCategoryById(Integer id) {
        return categoriesService.getCategoryById(id);
    }

    @GetMapping("/getCategoryByName/{name}")
    public Categories getCategoryByName(@PathVariable String name) {
        return categoriesService.getCategoryByName(name);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Map<String, String>> addCategory(@RequestBody Categories category) {
        Categories savedCategory = categoriesService.addCategory(category);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Category created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/updateCategory/{idCategory}")
    public ResponseEntity<Map<String, String>> updateCategory(Integer idCategory, Categories updatedCategory) {
        Categories category = categoriesService.updateCategory(idCategory, updatedCategory);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Category updated successfully");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/deactivateCategory/{idCategory}")
    public ResponseEntity<Map<String, String>> deactivateCategory(@PathVariable Integer idCategory) {
        categoriesService.deactivateCategory(idCategory);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Category deactivated successfully");
        return ResponseEntity.ok(response);
    }
}
