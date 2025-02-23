package com.empresa.empresa.Controllers.Products;


import com.empresa.empresa.Dto.Product.CharacteristicsDto;
import com.empresa.empresa.Models.Products.Characteristics;
import com.empresa.empresa.Services.Products.CharacteristicsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/characteristics")
public class CharacteristicsController {
    private final CharacteristicsService characteristicsService;

    public CharacteristicsController(CharacteristicsService characteristicsService){
        this.characteristicsService = characteristicsService;
    }

    //Get all Characteristics
    @GetMapping("/getAllCharacteristics")
    public Page<CharacteristicsDto> getAllCharacteristics(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size){
        return characteristicsService.getAllCharacteristics(page, size);
    }

    //Get Characteristics by Products
    @GetMapping("/getCharacteristicsByProducts/{idProduct}")
    public Page<CharacteristicsDto> getCharacteristicsByProducts(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @PathVariable Integer idProduct){
        return characteristicsService.getCharacteristicsByProducts(page, size, idProduct);
    }

    //Add Characteristics
    @PostMapping("/addCharacteristics/{idProduct}")
    public ResponseEntity<Map<String, String>> addCharacteristics(@RequestBody List<Characteristics> characteristics, @PathVariable Integer idProduct){
        List<Characteristics> savedCharacteristics = characteristicsService.addCharacteristics(characteristics, idProduct);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Characteristics created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Update Characteristics
    @PutMapping("/updateCharacteristics/{idCharacteristic}")
    public ResponseEntity<Map<String, String>> updateCharacteristics(@PathVariable Integer idCharacteristic, @RequestBody Characteristics characteristics){
        Characteristics updatedCharacteristics = characteristicsService.updateCharacteristics(idCharacteristic, characteristics);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Characteristics updated successfully");
        return ResponseEntity.ok(response);
    }

    //Delete Characteristics
    @DeleteMapping("/deleteCharacteristics/{idCharacteristic}")
    public ResponseEntity<Map<String, String>> deleteCharacteristics(@PathVariable Integer idCharacteristic){
        characteristicsService.deleteCharacteristics(idCharacteristic);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Characteristics deleted successfully");
        return ResponseEntity.ok(response);
    }
}
