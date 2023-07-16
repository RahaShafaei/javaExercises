package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfPaymentRepository extends JpaRepository<Payment,Long> {
}
