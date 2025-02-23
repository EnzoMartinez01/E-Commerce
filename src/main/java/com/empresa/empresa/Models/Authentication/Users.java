package com.empresa.empresa.Models.Authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.empresa.empresa.Deserializer.Authentication.RoleDeserializer;
import com.empresa.empresa.Models.Addresess.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "dbo_users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String names;
    @Column(nullable = false)
    private String lastnames;
    @Column(nullable = false, unique = true)
    private String dni;
    private String socialReason;
    private String telephone;
    private String fullname;
    @Column(nullable = false, unique = true)
    private String email;
    private LocalDate birthDate;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String username;
    private LocalDate created_at;
    private LocalDate connectionDate;
    private Boolean isActive;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Address> addresses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @JsonDeserialize(using = RoleDeserializer.class)
    private Roles role;

    //Historial de Contraseñas
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<PasswordHistory> passwordHistory = new ArrayList<>();

    public void addPasswordToHistory(String oldPassword) {
        PasswordHistory history = new PasswordHistory();
        history.setPassword(oldPassword);
        history.setUsers(this);
        passwordHistory.add(history);
    }

    //Verificación Correo
    private String verificationCode;
    private LocalDateTime verificationCodeExpiry;
}
