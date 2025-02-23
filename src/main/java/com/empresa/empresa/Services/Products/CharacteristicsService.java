package com.empresa.empresa.Services.Products;

import com.empresa.empresa.Dto.Product.CharacteristicsDto;
import com.empresa.empresa.Models.Products.Characteristics;
import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Repositories.Products.CharacteristicsRepository;
import com.empresa.empresa.Repositories.Products.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacteristicsService {
    private final static Logger logger = LoggerFactory.getLogger(CharacteristicsService.class);

    private final CharacteristicsRepository characteristicsRepository;
    private final ProductsRepository productsRepository;

    public CharacteristicsService(CharacteristicsRepository characteristicsRepository,
                                  ProductsRepository productsRepository) {
        this.characteristicsRepository = characteristicsRepository;
        this.productsRepository = productsRepository;
    }

    // Get all Characteristics
    public Page<CharacteristicsDto> getAllCharacteristics(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return characteristicsRepository.findAll(pageable).map(this::mapToDto);
    }

    // Get Characteristics by Products
    public Page<CharacteristicsDto> getCharacteristicsByProducts(int page, int size, Integer idProduct) {
        Products products = productsRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));
        Pageable pageable = PageRequest.of(page, size);
        return characteristicsRepository.findByProducts(pageable, products).map(this::mapToDto);
    }

    // Map to Dto
    public CharacteristicsDto mapToDto(Characteristics characteristics) {
        CharacteristicsDto dto = new CharacteristicsDto();
        dto.setIdCharacteristic(characteristics.getId());
        dto.setName(characteristics.getName());
        dto.setDescription(characteristics.getDescription());
        return dto;
    }

    // Add Characteristics
    public List<Characteristics> addCharacteristics(List<Characteristics> characteristics, Integer idProduct) {
        try {
            Products products = productsRepository.findById(idProduct)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));

            for (Characteristics characteristic : characteristics) {
                characteristic.setProducts(products);
            }

            return characteristicsRepository.saveAll(characteristics);
        } catch (Exception e) {
            logger.error("Error al añadir Characteristics", e);
            throw new RuntimeException("Error al añadir Characteristics", e);
        }
    }

    // Update Characteristics
    public Characteristics updateCharacteristics(Integer idCharacteristic, Characteristics updateCharacteristics) {
        try {
            Characteristics existingCharacteristics = characteristicsRepository.findById(idCharacteristic)
                    .orElseThrow(() -> new RuntimeException("Characteristics not found with ID: " + idCharacteristic));

            if (updateCharacteristics.getName() != null && !updateCharacteristics.getName().isEmpty()) {
                existingCharacteristics.setName(updateCharacteristics.getName());
            }
            if (updateCharacteristics.getDescription() != null && !updateCharacteristics.getDescription().isEmpty()) {
                existingCharacteristics.setDescription(updateCharacteristics.getDescription());
            }
            if (updateCharacteristics.getProducts() != null) {
                existingCharacteristics.setProducts(updateCharacteristics.getProducts());
            }

            return characteristicsRepository.save(existingCharacteristics);
        } catch (Exception e) {
            logger.error("Error al actualizar Characteristics", e);
            throw new RuntimeException("Error al actualizar Characteristics", e);
        }
    }

    // Delete Characteristics
    public void deleteCharacteristics(Integer idCharacteristic) {
        try {
            characteristicsRepository.findById(idCharacteristic).ifPresent(characteristicsRepository::delete);
        } catch (Exception e) {
            logger.error("Error al actualizar Characteristics", e);
            throw new RuntimeException("Error al actualizar Characteristics", e);
        }
    }
}