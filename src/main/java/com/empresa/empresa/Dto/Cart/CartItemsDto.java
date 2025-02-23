package com.empresa.empresa.Dto.Cart;

import com.empresa.empresa.Dto.Product.ProductsDto;
import com.empresa.empresa.Models.Cart.CartItems;
import lombok.Data;

import java.util.List;

@Data
public class CartItemsDto {
    private Integer idCartItem;
    private Integer quantity;
    private Double subTotal;
    private ProductsDto product;
}
