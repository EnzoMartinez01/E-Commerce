package com.empresa.empresa.Services.Products;

import com.empresa.empresa.Dto.Product.AttributesDto;
import com.empresa.empresa.Models.Products.Attributes;
import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Models.Products.SubCategories;
import com.empresa.empresa.Repositories.Products.AttributesRepository;
import com.empresa.empresa.Repositories.Products.ProductsRepository;
import com.empresa.empresa.Repositories.Products.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttributesService {
    private final static Logger logger = LoggerFactory.getLogger(AttributesService.class);

    private final AttributesRepository attributesRepository;
    private final ProductsRepository productsRepository;
    private final SubCategoryRepository subCategoryRepository;

    public AttributesService(AttributesRepository attributesRepository,
                             ProductsRepository productsRepository,
                             SubCategoryRepository subCategoryRepository) {
        this.attributesRepository = attributesRepository;
        this.productsRepository = productsRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    // Get All Attributes
    public Page<AttributesDto> getAllAttributes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return attributesRepository.findAll(pageable).map(this::mapToDto);
    }

    // Get Attributes by Products
    public Page<AttributesDto> getAttributesByProducts(int page, int size, Integer idProduct) {
        Products products = productsRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));
        Pageable pageable = PageRequest.of(page, size);
        return attributesRepository.findByProducts(pageable, products).map(this::mapToDto);
    }

    // Get Attributes by SubCategory
    public Page<AttributesDto> getAttributesBySubCategory(int page, int size, Integer idSubCategory) {
        SubCategories subCategory = subCategoryRepository.findById(idSubCategory)
                .orElseThrow(() -> new RuntimeException("SubCategory not found with ID: " + idSubCategory));
        Pageable pageable = PageRequest.of(page, size);
        return attributesRepository.findBySubCategories(pageable, subCategory).map(this::mapToDto);
    }

    // Map To Dto
    public AttributesDto mapToDto(Attributes attributes) {
        AttributesDto dto = new AttributesDto();
        dto.setIdAttribute(attributes.getId());
        dto.setAttributeName(attributes.getAttributeName());
        dto.setIdSubCategory(attributes.getSubCategories().getIdSubCategory());
        dto.setSubCategoryName(attributes.getSubCategories().getSubCategoryName());
        return dto;
    }

    // Add Attributes
    @Transactional
    public List<Attributes> addAttributes(List<Attributes> attributes, Integer idProduct) {
        try {
            Products products = productsRepository.findById(idProduct)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));

            for (Attributes attribute : attributes) {
                attribute.setProducts(products);
            }

            return attributesRepository.saveAll(attributes);
        } catch (Exception e) {
            logger.error("Error al añadir Attributes", e);
            throw new RuntimeException("Error al añadir Attributes", e);
        }
    }

    // Updated Attributes
    @Transactional
    public Attributes updateAttributes(Integer idAttribute, Attributes updateAttributes) {
        try {
            Attributes existingAttributes = attributesRepository.findById(idAttribute)
                    .orElseThrow(() -> new RuntimeException("Attributes not found with ID: " + idAttribute));

            if (updateAttributes.getAttributeName() != null && !updateAttributes.getAttributeName().isEmpty()) {
                existingAttributes.setAttributeName(updateAttributes.getAttributeName());
            }
            if (updateAttributes.getSubCategories() != null) {
                existingAttributes.setSubCategories(updateAttributes.getSubCategories());
            }
            if (updateAttributes.getProducts() != null) {
                existingAttributes.setProducts(updateAttributes.getProducts());
            }

            return attributesRepository.save(existingAttributes);
        } catch (Exception e) {
            logger.error("Error al actualizar Attributes", e);
            throw new RuntimeException("Error al actualizar Attributes", e);
        }
    }

    // Delete Attribute
    public void deleteAttribute(Integer idAttribute) {
        try {
            attributesRepository.findById(idAttribute).ifPresent(attributesRepository::delete);
        } catch (Exception e) {
            logger.error("Error al actualizar Attributes", e);
            throw new RuntimeException("Error al actualizar Attributes", e);
        }
    }
}
