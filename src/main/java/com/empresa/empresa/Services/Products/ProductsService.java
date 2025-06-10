package com.empresa.empresa.Services.Products;

import com.empresa.empresa.Dto.Product.AttributesDto;
import com.empresa.empresa.Dto.Product.CharacteristicsDto;
import com.empresa.empresa.Dto.Product.ProductsDto;
import com.empresa.empresa.Models.Products.Attributes;
import com.empresa.empresa.Models.Products.Characteristics;
import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Repositories.Products.ProductsRepository;
import com.empresa.empresa.Services.Reports.AuditLogService;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private final static Logger logger = LoggerFactory.getLogger(ProductsService.class);

    private final ProductsRepository productsRepository;
    private final AuditLogService auditLogService;

    public ProductsService(ProductsRepository productsRepository,
                           AuditLogService auditLogService) {
        this.productsRepository = productsRepository;
        this.auditLogService = auditLogService;
    }

    // Get all Products
    public Page<ProductsDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productsRepository.findAll(pageable).map(this::mapToDto);
    }

    //Get Products by Id
    public ProductsDto getProductsById(Integer idProduct) {
        try {
            return productsRepository.findById(idProduct)
                    .map(this::mapToDto)
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + idProduct));
        } catch (Exception e) {
            logger.error("Error al obtener producto con ID: {}", idProduct, e);
            throw new RuntimeException("Error al obtener producto", e);
        }
    }

    //Get Products by Filters
    public Page<ProductsDto> getFilteredProducts(String searchTerms,
                                                 Double minPrice, Double maxPrice, Integer stock,
                                                 Boolean isOffer, Integer brandId,
                                                 Integer categoryId, Integer subCategoryId,
                                                 List<Integer> attributeIds, Boolean isActive,
                                                 int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            String search = (searchTerms != null && !searchTerms.trim().isEmpty()) ? searchTerms.trim() : null;
            return productsRepository.findByFilters(search, minPrice, maxPrice, stock, isOffer, brandId, categoryId, subCategoryId, attributeIds, isActive, pageable)
                    .map(this::mapToDto);
        } catch (Exception e) {
            logger.error("Error al obtener los productos con filtros", e);
            throw new RuntimeException("Error al obtener los productos con filtros", e);
        }
    }


    // Map to Dto
    public ProductsDto mapToDto (Products products) {
        ProductsDto dto = new ProductsDto();
        dto.setIdProduct(products.getId());
        dto.setProductName(products.getProductName());
        dto.setProductDescription(products.getProductDescription());
        dto.setProductSku(products.getSku());
        dto.setProductPrice(products.getPrice());
        dto.setPriceCreditCard(products.getPrice_creditcard());
        dto.setQuantity(products.getQuantity());
        dto.setStock(products.getStock());
        dto.setProductOfferDiscount(products.getOfferDescount());
        dto.setPriceOffer(products.getPriceOffer());
        dto.setIdBrand(products.getBrand().getIdBrand());
        dto.setBrandName(products.getBrand().getBrandName());
        dto.setBrandImage(products.getBrand().getBrandImage());
        dto.setIdCategory(products.getCategory().getIdCategory());
        dto.setCategoryName(products.getCategory().getCategoryName());
        dto.setPdfFile(products.getPdfFile());
        dto.setIsOffer(products.getIsOffer());
        dto.setIsActive(products.getIsActive());
        dto.setProductImg(products.getProduct_image());
        dto.setAttributes(products.getAttributes().stream().map(this::mapToAttributeDto).toList());
        dto.setCharacteristics(products.getCharacteristics().stream().map(this::mapToCharacteristicDto).toList());
        return dto;
    }

    // Map to Attribute Dto
    public AttributesDto mapToAttributeDto(Attributes attributes) {
        AttributesDto dto = new AttributesDto();
        dto.setIdAttribute(attributes.getId());
        dto.setAttributeName(attributes.getAttributeName());
        dto.setIdSubCategory(attributes.getSubCategories().getIdSubCategory());
        dto.setSubCategoryName(attributes.getSubCategories().getSubCategoryName());
        return dto;
    }

    // Map to Characteristic Dto
    public CharacteristicsDto mapToCharacteristicDto(Characteristics characteristics) {
        CharacteristicsDto dto = new CharacteristicsDto();
        dto.setIdCharacteristic(characteristics.getId());
        dto.setName(characteristics.getName());
        dto.setDescription(characteristics.getDescription());
        return dto;
    }

    // Add Prodcut
    public Products addProduct (Products products) {
        try {
            Double price = products.getPrice();
            if (products.getPrice() != null) {
                Double priceCreditCard = price + (products.getPrice() * 0.05);
                products.setPrice_creditcard(priceCreditCard);
            }

            if (products.getIsOffer() != null && products.getIsOffer() && products.getOfferDescount() != null && products.getPrice() != null) {
                Double discount = price * (products.getPriceOffer() / 100.0);
                Double discountPrice = price - discount;
                products.setPriceOffer(discountPrice);
            }

            products.setIsActive(true);

            auditLogService.logActionCreated("SE AÑADIÓ PRODUCTO: " + products.getProductName());

            return productsRepository.save(products);
        } catch (Exception e) {
            logger.error("Error al añadir Producto", e);
            throw new RuntimeException("Error al añadir Producto", e);
        }
    }

    // Created offer Product
    public Products createProductOffer(Integer idProduct, Products products) {
        try {
            Products existingProduct = productsRepository.findById(idProduct)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));

            Double price = existingProduct.getPrice();

            if (products.getIsOffer() != null && products.getIsOffer() && products.getOfferDescount() != null && price != null) {
                Double discount = price * (products.getOfferDescount() / 100.0);
                Double discountPrice = price - discount;
                existingProduct.setPriceOffer(discountPrice);
                existingProduct.setIsOffer(true);
                existingProduct.setOfferDescount(products.getOfferDescount());
            }

            auditLogService.logActionCreated("SE AÑADIÓ OFERTA DE PRODUCTO: " + existingProduct.getProductName());

            return productsRepository.save(existingProduct);
        } catch (Exception e) {
            logger.error("Error al añadir Producto", e);
            throw new RuntimeException("Error al añadir Producto", e);
        }
    }

    // Deactivate offer Product
    public void deactivateProductOffer(Integer idProduct) {
        try {
            Products existingProduct = productsRepository.findById(idProduct)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));

            if (existingProduct.getIsOffer() != null && existingProduct.getIsOffer()) {
                existingProduct.setIsOffer(false);
                existingProduct.setOfferDescount(null);
                existingProduct.setPriceOffer(null);
            } else {
                throw new RuntimeException("El producto no tiene una oferta activa");
            }

            auditLogService.logActionCreated("SE DESACTIVÓ LA OFERTA DE PRODUCTO: " + existingProduct.getProductName());

            productsRepository.save(existingProduct);
        } catch (Exception e) {
            logger.error("Error al desactivar la oferta del producto", e);
            throw new RuntimeException("Error al desactivar la oferta del producto", e);
        }
    }


    // Updated Product
    public Products updateProduct(Integer idProduct, Products updatedProduct) {
        try {
            Products existingProduct = productsRepository.findById(idProduct)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));

            if (updatedProduct.getProductName() != null && !updatedProduct.getProductName().isEmpty()) {
                existingProduct.setProductName(updatedProduct.getProductName());
            }
            if (updatedProduct.getProductDescription() != null && !updatedProduct.getProductDescription().isEmpty()) {
                existingProduct.setProductDescription(updatedProduct.getProductDescription());
            }
            if (updatedProduct.getSku() != null && !updatedProduct.getSku().isEmpty()) {
                existingProduct.setSku(updatedProduct.getSku());
            }
            if (updatedProduct.getPrice() != null) {
                existingProduct.setPrice(updatedProduct.getPrice());
            }

            if (updatedProduct.getQuantity() != null) {
                existingProduct.setQuantity(updatedProduct.getQuantity());
            }

            if (updatedProduct.getProduct_image() != null && !updatedProduct.getProduct_image().isEmpty()) {
                existingProduct.setProduct_image(updatedProduct.getProduct_image());
            }

            if (updatedProduct.getStock() != null) {
                existingProduct.setStock(updatedProduct.getStock());
            }
            if (updatedProduct.getOfferDescount() != null) {
                existingProduct.setOfferDescount(updatedProduct.getOfferDescount());
            }

            if (updatedProduct.getIsOffer() != null) {
                existingProduct.setIsOffer(updatedProduct.getIsOffer());
            }
            if (updatedProduct.getPdfFile() != null && !updatedProduct.getPdfFile().isEmpty()) {
                existingProduct.setPdfFile(updatedProduct.getPdfFile());
            }
            if (updatedProduct.getBrand() != null) {
                existingProduct.setBrand(updatedProduct.getBrand());
            }
            if (updatedProduct.getCategory() != null) {
                existingProduct.setCategory(updatedProduct.getCategory());
            }

            if (updatedProduct.getIsActive() != null) {
                existingProduct.setIsActive(updatedProduct.getIsActive());
            }

            Double price = existingProduct.getPrice();
            if (price != null) {
                Double priceCreditCard = price + (price * 0.05);
                existingProduct.setPrice_creditcard(priceCreditCard);
            }

            if (updatedProduct.getIsOffer() != null && updatedProduct.getIsOffer() && updatedProduct.getOfferDescount() != null && price != null) {
                Double discount = price * (updatedProduct.getOfferDescount() / 100.0);
                Double discountPrice = price - discount;
                existingProduct.setPriceOffer(discountPrice);
                existingProduct.setIsOffer(true);
                existingProduct.setOfferDescount(updatedProduct.getOfferDescount());
            }

            auditLogService.logActionUpdated("SE ACTUALIZÓ PRODUCTO: " + existingProduct.getProductName());

            return productsRepository.save(existingProduct);
        } catch (Exception e) {
            logger.error("Error al actualizar Producto", e);
            throw new RuntimeException("Error al actualizar Producto", e);
        }
    }


    // Deactivate Product
    public void deactivateProduct(Integer idProduct) {
        Products products = productsRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found: " + idProduct));

        if (!products.getIsActive()) {
            throw new IllegalStateException("Product id already deactivated.");
        }

        auditLogService.logActionDeleted("SE DESACTIVÓ PRODUCTO: " + products.getProductName());
        products.setIsActive(false);
        productsRepository.save(products);
    }



   
}
