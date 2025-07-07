package com.empresa.empresa.Models.Cart;

import com.empresa.empresa.Deserializer.Authentication.UsersDeserializer;
import com.empresa.empresa.Deserializer.Cart.CartDeserializer;
import com.empresa.empresa.Deserializer.Products.ProductsDeserializer;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Products.Products;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dbo_cartItems")
@Getter
@Setter
@ToString(exclude = "cart")
@EqualsAndHashCode(exclude = "cart")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonDeserialize(using = CartDeserializer.class)
    @JsonBackReference
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonDeserialize(using = ProductsDeserializer.class)
    private Products product;

    private Integer quantity;
    private Double subTotal;
}
