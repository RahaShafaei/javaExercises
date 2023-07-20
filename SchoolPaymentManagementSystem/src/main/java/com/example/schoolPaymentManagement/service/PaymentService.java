package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.model.Payment;

import java.util.List;

public interface PaymentService {

    public List<PaymentDto> getPayments();

    public PaymentDto getPayment(Long id);

    public PaymentDto getPartOfPayment(Long id, String type);

    public Boolean deletePayment(Long id);

    public PaymentDto createPaymentOfSpecificType(Payment payment, String type, Long id);
}
