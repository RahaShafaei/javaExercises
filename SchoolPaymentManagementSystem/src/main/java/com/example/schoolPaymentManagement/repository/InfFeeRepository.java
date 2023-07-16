package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InfFeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findAllByStatusNullAndDeadLineBeforeAndPaymentNull(LocalDate deadLine);

    List<Fee> findAllByStatusNullAndDeadLineGreaterThanEqualOrPaymentNotNull(LocalDate deadLine);
}
