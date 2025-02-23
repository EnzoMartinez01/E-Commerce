package com.empresa.empresa.Models.Addresess;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dbo_country")
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
