package com.empresa.empresa.Models.Contact;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dbo_status_contact")
@Data
public class StatusContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
