package com.empresa.empresa.Repositories.Payments;

import com.empresa.empresa.Models.Payments.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
