package com.empresa.empresa.Models.Cart;

import com.empresa.empresa.Deserializer.Authentication.UsersDeserializer;
import com.empresa.empresa.Deserializer.Cart.PaymentMethodDeserializer;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Payments.Payment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dbo_cart")
@Getter
@Setter
@ToString(exclude = "cartItems")
@EqualsAndHashCode(exclude = "cartItems")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonDeserialize(using = UsersDeserializer.class)
    private Users users;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TypeShipment typeShipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentMethod_id", nullable = false)
    @JsonDeserialize(using = PaymentMethodDeserializer.class)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CartItems> cartItems = new ArrayList<>();

    private Double total;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();
}
