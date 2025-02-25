package com.empresa.empresa.Services.Products;

import com.empresa.empresa.Models.Products.Brands;
import com.empresa.empresa.Repositories.Products.BrandsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandsService {
    private final static Logger logger = LoggerFactory.getLogger(BrandsService.class);

    private final BrandsRepository brandsRepository;

    public BrandsService(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    //Get all brands
    public Page<Brands> getAllBrands(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return brandsRepository.findAll(pageable);
    }

    // Get all Brands without Pagination
    public List<Brands> getAllBrands() {
        return brandsRepository.findAllByIsActive(true);
    }

    //Get Brand by ID
    public Brands getBrandById(Integer id) {
        return brandsRepository.findById(id).orElse(null);
    }

    //Add Brand
    public Brands addBrand(Brands brand) {
        brand.setIsActive(true);
        return brandsRepository.save(brand);
    }

    //Update Brand
    public Brands updateBrand(Integer idBrand, Brands updatedBrand) {
        Brands existingBrand = brandsRepository.findById(idBrand)
                .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + idBrand));

        existingBrand.setBrandName(updatedBrand.getBrandName() != null ? updatedBrand.getBrandName() : existingBrand.getBrandName());
        existingBrand.setIsActive(updatedBrand.getIsActive() != null ? updatedBrand.getIsActive() : existingBrand.getIsActive());
        existingBrand.setBrandImage(updatedBrand.getBrandImage() != null ? updatedBrand.getBrandImage() : existingBrand.getBrandImage());

        return brandsRepository.save(existingBrand);
    }

    //Deactivate Brand
    public void deactivateBrand(Integer idBrand) {
        Brands brand = brandsRepository.findById(idBrand)
                .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + idBrand));

        if (!brand.getIsActive()) {
            throw new IllegalStateException("Brand is already deactivated.");
        }

        brand.setIsActive(false);
        brandsRepository.save(brand);
    }
}
