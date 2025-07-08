package com.empresa.empresa.Repositories.Payments;

import com.empresa.empresa.Models.Payments.Payment;
import com.empresa.empresa.Models.Payments.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByStatusInAndEmailSentFalse(List<PaymentStatus> statuses);

    @Query("SELECT p FROM Payment p WHERE " +
            "LOWER(p.reference) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(p.cart.users.username) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Payment> findByReferenceOrUsername(@Param("search") String search, Pageable pageable);


    @Query("SELECT p FROM Payment p WHERE " +
            "(LOWER(p.reference) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(p.cart.users.username) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND p.status = :status")
    Page<Payment> findByReferenceOrUsernameAndStatus(@Param("search") String search,
                                                     @Param("status") PaymentStatus status,
                                                     Pageable pageable);

}
