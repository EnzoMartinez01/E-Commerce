package com.empresa.empresa.Controllers.Reports;

import com.empresa.empresa.Dto.Reports.AuditLogDto;
import com.empresa.empresa.Services.Reports.AuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/AuditLogs")
public class AuditLogController {
    private final static Logger logger = LoggerFactory.getLogger(AuditLogController.class);
    private final AuditLogService auditLogService;

    public  AuditLogController(AuditLogService auditLogService){
        this.auditLogService = auditLogService;
    }

    //Get all Audit Log
    @GetMapping("/getAllAuditLogs")
    public Page<AuditLogDto> getallAuditLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return auditLogService.getAllAuditLogs(page, size);
    }

    //Get Audit Log by User
    @GetMapping("/getAuditLogsByUser/{idUser}")
    public Page<AuditLogDto> getallAuditLogsByUser(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @PathVariable Integer idUser){
        return auditLogService.getAuditLogsByUser(page, size, idUser);
    }
}
