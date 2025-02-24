package com.empresa.empresa.Controllers.Products;

import com.empresa.empresa.Dto.Product.ProductsDto;
import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Services.Products.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }

    //Get all Products
    @GetMapping("/getAllProducts")
    public Page<ProductsDto> getallProducts(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size){
        return  productsService.getAllProducts(page, size);
    }

    //Get Products by Filter
    @GetMapping("/getProductsByFilters")
    public Page<ProductsDto> getProductsByFilters(
            @RequestParam(required = false) String searchTerms,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Boolean isOffer,
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size){

        return productsService.getFilteredProducts(searchTerms,price,stock,isOffer,brandId,categoryId,isActive,page,size);
    }

    //Get Products by Id
    @GetMapping("/getProductsById/{idProducts}")
    public ProductsDto getProductsByDto
    (@PathVariable Integer idProducts ) 
    {
        return productsService.geProductsById(idProducts);
    } 


    //Add Product
   @PostMapping("/addProduct")
   public ResponseEntity<Map<String, String>> addProduct(@RequestBody Products products){
        Products savedProduct = productsService.addProduct(products);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }

   //Created offer Product
    @PutMapping("/updateProductOffer/{idProduct}")
    public ResponseEntity<Map<String, String>> createProductOffer(@PathVariable Integer idProduct,
                                                                  @RequestBody Products products){
        Products product = productsService.createProductOffer(idProduct, products);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product offer created successfully");
        return ResponseEntity.ok(response);
    }

    //Updated Product
    @PutMapping("/updateProduct/{idProduct}")
    public ResponseEntity<Map<String, String>> updateProduct(@PathVariable Integer idProduct,
                                                             @RequestBody Products updatedProduct){
        Products product = productsService.updateProduct(idProduct, updatedProduct);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product updated successfully");
        return ResponseEntity.ok(response);
    }

    //Deactivate Product
    @PatchMapping("/deactivateProduct/{idProduct}")
    public ResponseEntity<Map<String, String>> deactivateProduct(@PathVariable Integer idProduct){
        productsService.deactivateProduct(idProduct);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product deactivated successfully");
        return ResponseEntity.ok(response);
    }

   

}
