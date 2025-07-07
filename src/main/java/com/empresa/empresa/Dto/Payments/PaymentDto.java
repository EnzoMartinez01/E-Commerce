package com.empresa.empresa.Dto.Payments;

import com.empresa.empresa.Dto.Cart.CartItemsDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class PaymentDto {
    private Integer idPayment;
    // Users
    private String fullName;
    private String email;
    private String dni;
    private String username;

    // Cart Items
    private List<CartItemsDto> cartItems;

    // Cart
    private String paymentMethod;
    private String typeShipment;
    private Double totalAmount;

    private String reference;
    private LocalDateTime datePayment;
    private String voucherFile;
    private Double amount;
    private String status;
}
