package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfFeePaymentRepository extends JpaRepository<FeePayment,Long> {
}
