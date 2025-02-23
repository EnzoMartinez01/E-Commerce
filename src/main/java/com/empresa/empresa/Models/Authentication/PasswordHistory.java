package com.empresa.empresa.Models.Authentication;

import com.empresa.empresa.Deserializer.Authentication.UsersDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "dbo_passwordHistory")
@Data

public class PasswordHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonDeserialize(using = UsersDeserializer.class)
    private Users users;

    @Column(nullable = false)
    private LocalDateTime date_change = LocalDateTime.now();

}
