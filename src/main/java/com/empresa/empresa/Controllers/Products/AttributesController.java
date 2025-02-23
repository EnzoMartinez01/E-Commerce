package com.empresa.empresa.Controllers.Products;

import com.empresa.empresa.Dto.Product.AttributesDto;
import com.empresa.empresa.Models.Products.Attributes;
import com.empresa.empresa.Services.Products.AttributesService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/attributes")
public class AttributesController {
    private final AttributesService attributesService;

    public AttributesController(AttributesService attributesService){
        this.attributesService = attributesService;
    }

    //Get all Attributes
    @GetMapping("/getAllAttributes")
    public Page<AttributesDto> getallAttributes(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size) {
        return attributesService.getAllAttributes(page, size);
    }

    //Get Attributes by Products
    @GetMapping("/getAttributesByProducts/{idProduct}")
    public Page<AttributesDto> getAttributesByProducts(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @PathVariable Integer idProduct){
        return attributesService.getAttributesByProducts(page, size, idProduct);
    }

    //Get Attributes by SubCategory
    @GetMapping("/getAttributesBySubCategory/{idSubCategory}")
    public Page<AttributesDto> getAttributesBySubcategory(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @PathVariable Integer idSubCategory){
        return attributesService.getAttributesByProducts(page, size, idSubCategory);
    }

    //Add Attributes
    @PostMapping("/addAttributes/{idProduct}")
    public ResponseEntity<Map<String, String>> addAttributes(
            @PathVariable Integer idProduct,
            @RequestBody List<Attributes> addAttributes){
        List<Attributes> attributes = attributesService.addAttributes(addAttributes, idProduct);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Atribute add successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //Updated Attributes
    @PutMapping("/updatedAttributes/{idAttribute}")
    public ResponseEntity<Map<String, String>> updateAttributes(@PathVariable Integer idAttribute, @RequestBody Attributes updatedAttributes){
        Attributes attribute = attributesService.updateAttributes(idAttribute, updatedAttributes);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Attributes updated successfully");
        return ResponseEntity.ok(response);
    }

    //Delete Attributes
    @DeleteMapping("/deleteAttributes/{idAttribute}")
    public ResponseEntity<Map<String, String>> deleteAttributes(@PathVariable Integer idAttribute){
        attributesService.deleteAttribute(idAttribute);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Attributes deleted successfully");
        return ResponseEntity.ok(response);
    }
}
