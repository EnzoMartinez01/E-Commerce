package com.empresa.empresa.Models.Reports;

import com.empresa.empresa.Deserializer.Authentication.UsersDeserializer;
import com.empresa.empresa.Models.Authentication.Users;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "dbo_audit_log")
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonDeserialize(using = UsersDeserializer.class)
    private Users performedBy;

    private LocalDateTime performed_at;
    private String details;
}
