package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfGradeRepository extends JpaRepository<Grade, Long> {
}
