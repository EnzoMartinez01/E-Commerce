package com.empresa.empresa.Controllers.Products;

import com.empresa.empresa.Models.Products.Brands;
import com.empresa.empresa.Services.Products.BrandsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandsController {
    private final BrandsService brandsService;

    public BrandsController(BrandsService brandsService) {
        this.brandsService = brandsService;
    }

    @GetMapping("/getAllBrands")
    public Page<Brands> getAllBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return brandsService.getAllBrands(page, size);
    }

    @GetMapping("/getBrand/{id}")
    public Brands getBrandById(Integer id){
        return brandsService.getBrandById(id);
    }

    @PostMapping("/addBrand")
    public ResponseEntity<Map<String, String>> addBrand(Brands brand){
        Brands savedBrand = brandsService.addBrand(brand);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Brand created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/updateBrand/{idBrand}")
    public ResponseEntity<Map<String, String>> updateBrand(Integer idBrand, Brands updatedBrand){
        Brands brand = brandsService.updateBrand(idBrand, updatedBrand);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Brand updated successfully");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/deactivateBrand/{idBrand}")
    public ResponseEntity<Map<String, String>> deactivateBrand(Integer idBrand){
        brandsService.deactivateBrand(idBrand);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Brand deactivated successfully");
        return ResponseEntity.ok(response);
    }
}
