package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InfSalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findAllByStatusNullAndDeadLineBeforeAndPaymentNull(LocalDate deadLine);

    List<Salary> findAllByStatusNullAndDeadLineGreaterThanEqualOrPaymentNotNull(LocalDate deadLine);

}
