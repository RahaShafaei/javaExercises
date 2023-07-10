package com.example.schoolPaymentManagement.repository;

import com.example.schoolPaymentManagement.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfFeeRepository extends JpaRepository<Fee,Long> {
}
