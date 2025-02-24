package com.empresa.empresa.Dto.Product;

import lombok.Data;

import java.util.List;

@Data
public class ProductsDto {
    private Integer idProduct;
    private String productName;
    private String productDescription;
    private String productSku;
    private Double productPrice;
    private Double priceCreditCard;
    private Integer quantity;
    private Integer stock;
    private Integer productOfferDiscount;
    private Double priceOffer;
    private Integer idBrand;
    private String brandName;
    private String brandImage;
    private Integer idCategory;
    private String categoryName;
    private String pdfFile;
    private Boolean isOffer;
    private Boolean isActive;
    private String productImg;
    private List<AttributesDto> attributes;
    private List<CharacteristicsDto> characteristics;
}
