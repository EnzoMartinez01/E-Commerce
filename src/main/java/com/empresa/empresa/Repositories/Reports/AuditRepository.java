package com.empresa.empresa.Repositories.Reports;

import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Reports.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Integer> {
    Page<AuditLog> findByPerformedBy(Pageable pageable, Users users);
}
