package com.empresa.empresa.Services.Reports;

import com.empresa.empresa.Dto.Reports.AuditLogDto;
import com.empresa.empresa.Models.Authentication.CustomUserDetails;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Reports.Action;
import com.empresa.empresa.Models.Reports.AuditLog;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import com.empresa.empresa.Repositories.Reports.AuditRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AuditLogService {
    private final static Logger logger = LoggerFactory.getLogger(AuditLogService.class);

    private final AuditRepository auditRepository;
    private final UsersRepository usersRepository;

    public AuditLogService(AuditRepository auditRepository,
                           UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.auditRepository = auditRepository;
    }

    //Get all Audit Logs
    public Page<AuditLogDto> getAllAuditLogs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        logActionRead("Get all Audit Logs");

        return auditRepository.findAll(pageable).map(this::mapToDto);
    }

    //Get Audit Logs by User
    public Page<AuditLogDto> getAuditLogsByUser(int page, int size, Integer idUser) {
        Users users = usersRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        logActionRead("Get Audit Logs: " + users.getFullname());

        Pageable pageable = PageRequest.of(page, size);
        return auditRepository.findByPerformedBy(pageable, users).map(this::mapToDto);
    }

    // Map To Dto
    public AuditLogDto mapToDto(AuditLog auditLog) {
        AuditLogDto dto = new AuditLogDto();
        dto.setId(auditLog.getId());
        dto.setAction(auditLog.getAction().name());
        dto.setIdPreformedBy(auditLog.getPerformedBy().getId());
        dto.setPreformedBy(auditLog.getPerformedBy().getFullname());
        dto.setDetails(auditLog.getDetails());
        dto.setCreated_at(auditLog.getPerformed_at());
        return dto;
    }

    // Log Action CREATED
    public void logActionCreated(String details) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Users users = usersRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuditLog auditLog = new AuditLog();
            auditLog.setAction(Action.CREATED);
            auditLog.setPerformed_at(LocalDateTime.now());
            auditLog.setPerformedBy(users);
            auditLog.setDetails(details);

            auditRepository.save(auditLog);
        } catch (Exception e) {
            logger.error("Error al crear AuditLog", e);
            throw new RuntimeException("Error al crear AuditLog", e);
        }
    }

    //Log Action READ
    public void logActionRead(String details) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Users users = usersRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuditLog auditLog = new AuditLog();
            auditLog.setAction(Action.READ);
            auditLog.setPerformed_at(LocalDateTime.now());
            auditLog.setPerformedBy(users);
            auditLog.setDetails(details);

            auditRepository.save(auditLog);
        } catch (Exception e) {
            logger.error("Error al leer AuditLog", e);
            throw new RuntimeException("Error al leer AuditLog", e);
        }
    }

    // Log Action UPDATED
    public void logActionUpdated(String details) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Users users = usersRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuditLog auditLog = new AuditLog();
            auditLog.setAction(Action.UPDATED);
            auditLog.setPerformed_at(LocalDateTime.now());
            auditLog.setPerformedBy(users);
            auditLog.setDetails(details);

            auditRepository.save(auditLog);
        } catch (Exception e) {
            logger.error("Error al modificar Producto", e);
            throw new RuntimeException("Error al modificar Producto", e);
        }
    }

    //Log Action DELETED
    public void logActionDeleted(String details) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Users users = usersRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            AuditLog auditLog = new AuditLog();
            auditLog.setAction(Action.DELETED);
            auditLog.setPerformed_at(LocalDateTime.now());
            auditLog.setPerformedBy(users);
            auditLog.setDetails(details);

            auditRepository.save(auditLog);
        } catch (Exception e) {
            logger.error("Error al eliminar Producto", e);
            throw new RuntimeException("Error al eliminar Producto", e);
        }
    }

    // Exportar archivo Excel con registros de auditoría
    public void exportAuditLogsToExcel(HttpServletResponse response) throws IOException {
        List<AuditLog> auditLogs = auditRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Audit Logs");

            CellStyle borderedStyle = workbook.createCellStyle();
            borderedStyle.setBorderTop(BorderStyle.THIN);
            borderedStyle.setBorderBottom(BorderStyle.THIN);
            borderedStyle.setBorderLeft(BorderStyle.THIN);
            borderedStyle.setBorderRight(BorderStyle.THIN);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.cloneStyleFrom(borderedStyle);
            headerStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);

            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.cloneStyleFrom(borderedStyle);
            CreationHelper createHelper = workbook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

            String[] columns = {"ID", "Acción", "ID Usuario", "Nombre", "Detalles", "Fecha"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (AuditLog log : auditLogs) {
                Row row = sheet.createRow(rowNum++);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue(log.getId());
                cell0.setCellStyle(borderedStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(log.getAction().name());
                cell1.setCellStyle(borderedStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(log.getPerformedBy().getId());
                cell2.setCellStyle(borderedStyle);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(log.getPerformedBy().getFullname());
                cell3.setCellStyle(borderedStyle);

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(log.getDetails());
                cell4.setCellStyle(borderedStyle);

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(log.getPerformed_at());
                cell5.setCellStyle(dateStyle);
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=audit-logs.xlsx");

            workbook.write(response.getOutputStream());
        }
    }
}
