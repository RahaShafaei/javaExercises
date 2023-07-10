package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfSalaryRepository extends JpaRepository<Salary,Long> {
}
