package com.empresa.empresa.Models.Cart;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dbo_payment_method")
@Data
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
