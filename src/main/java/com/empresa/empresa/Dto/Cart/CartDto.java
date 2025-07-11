package com.empresa.empresa.Dto.Cart;

import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Cart.CartItems;
import com.empresa.empresa.Models.Cart.PaymentMethod;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
   private Integer idCart;

   private Integer idUser;
   private String fullName;

   private String typeShipment;

   private Integer idPaymentMethod;
   private String paymentMethod;

   private List<CartItemsDto> cartItems;
   private Double igv;
   private Double total;
}
