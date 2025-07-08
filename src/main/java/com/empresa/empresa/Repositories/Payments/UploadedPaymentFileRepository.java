package com.empresa.empresa.Repositories.Payments;

import com.empresa.empresa.Models.Payments.Payment;
import com.empresa.empresa.Models.Payments.UploadedPaymentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UploadedPaymentFileRepository extends JpaRepository<UploadedPaymentFile, Integer> {
    List<UploadedPaymentFile> findByPayment(Payment payment);
    Optional<UploadedPaymentFile> findByPayment_Id(Integer paymentId);
}
