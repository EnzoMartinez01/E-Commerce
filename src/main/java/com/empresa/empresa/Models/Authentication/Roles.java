package com.empresa.empresa.Models.Authentication;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "dbo_roles")
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
