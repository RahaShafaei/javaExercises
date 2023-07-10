package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfSalaryPaymentRepository extends JpaRepository<SalaryPayment,Long> {
}
