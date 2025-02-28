package com.empresa.empresa.Models.Contact;

import com.empresa.empresa.Deserializer.Authentication.UsersDeserializer;
import com.empresa.empresa.Deserializer.Contact.StatusContactDeserializer;
import com.empresa.empresa.Models.Authentication.Users;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "dbo_contact")
@Data

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String names;
    private String lastname;
    private String social_reason;
    private String email;
    private String dniRuc;
    private String telephone;
    private String message;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonDeserialize(using = UsersDeserializer.class)
    private Users create_by;

    private LocalDateTime create_at;
    private Boolean is_agree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonDeserialize(using = StatusContactDeserializer.class)
    private StatusContact status;
}
