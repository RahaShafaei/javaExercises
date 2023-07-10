package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfStudentRepository extends JpaRepository<Student,Long> {
}
