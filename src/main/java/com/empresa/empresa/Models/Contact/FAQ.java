package com.empresa.empresa.Models.Contact;

import com.empresa.empresa.Deserializer.Authentication.UsersDeserializer;
import com.empresa.empresa.Models.Authentication.Users;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name= "dbo_FAQ")
@Data

public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonDeserialize(using = UsersDeserializer.class)
    private Users createdBy;

    private LocalDateTime created_at;
}
