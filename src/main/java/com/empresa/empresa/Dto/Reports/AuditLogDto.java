package com.empresa.empresa.Dto.Reports;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditLogDto {
    private Integer id;
    private String action;
    private Integer idPreformedBy;
    private String preformedBy;
    private String details;
    private LocalDateTime created_at;
}
