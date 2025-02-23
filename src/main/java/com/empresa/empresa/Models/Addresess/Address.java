package com.empresa.empresa.Models.Addresess;

import com.empresa.empresa.Deserializer.Addresses.CountryDeserializer;
import com.empresa.empresa.Deserializer.Addresses.DistrictsDeserializer;
import com.empresa.empresa.Deserializer.Addresses.ProvinceDeserializer;
import com.empresa.empresa.Deserializer.Addresses.StateDeserializer;
import com.empresa.empresa.Models.Authentication.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street_name;
    private String street_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonDeserialize(using = CountryDeserializer.class)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonDeserialize(using = StateDeserializer.class)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonDeserialize(using = ProvinceDeserializer.class)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonDeserialize(using = DistrictsDeserializer.class)
    private Districts district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Users users;
}
