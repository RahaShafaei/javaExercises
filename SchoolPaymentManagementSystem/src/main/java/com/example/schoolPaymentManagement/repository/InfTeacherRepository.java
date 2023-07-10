package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfTeacherRepository extends JpaRepository<Teacher,Long> {
}
