package com.empresa.empresa.Controllers.Products;

import com.empresa.empresa.Models.Products.Brands;
import com.empresa.empresa.Services.Products.BrandsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandsController {
    private final BrandsService brandsService;

    public BrandsController(BrandsService brandsService) {
        this.brandsService = brandsService;
    }

    //Get all Brands with Pagination
    @GetMapping("/getAllBrands")
    public Page<Brands> getAllBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return brandsService.getAllBrands(page, size);
    }

    // Get all Brands Images
    @GetMapping("/getAllBrandsImages")
    public List<String> getAllBrandsImages(){
        return brandsService.getAllBrands()
                .stream()
                .map(Brands::getBrandImage)
                .collect(Collectors.toList());
    }

    //Get Brand by ID
    @GetMapping("/getBrand/{id}")
    public Brands getBrandById(@PathVariable Integer id){
        return brandsService.getBrandById(id);
    }

    //Add Brand
    @PostMapping("/addBrand")
    public ResponseEntity<Map<String, String>> addBrand(@RequestBody Brands brand){
        Brands savedBrand = brandsService.addBrand(brand);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Brand created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Update Brand
    @PutMapping("/updateBrand/{idBrand}")
    public ResponseEntity<Map<String, String>> updateBrand(@PathVariable Integer idBrand, @RequestBody Brands updatedBrand){
        Brands brand = brandsService.updateBrand(idBrand, updatedBrand);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Brand updated successfully");
        return ResponseEntity.ok(response);
    }

    //Deactivate Brand
    @PatchMapping("/deactivateBrand/{idBrand}")
    public ResponseEntity<Map<String, String>> deactivateBrand(@PathVariable Integer idBrand){
        brandsService.deactivateBrand(idBrand);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Brand deactivated successfully");
        return ResponseEntity.ok(response);
    }
}
