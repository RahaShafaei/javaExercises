package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Payment;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To convert {@link Payment} to {@link PaymentDto}.
 * </p>
 */
@Component
public class PaymentMapper {

    private final SalaryMapper salaryMapper;
    private final FeeMapper feeMapper;

    public PaymentMapper(SalaryMapper salaryMapper,
                         FeeMapper feeMapper) {
        this.salaryMapper = salaryMapper;
        this.feeMapper = feeMapper;
    }

    public PaymentDto toDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setPaymentId(payment.getPaymentId());
        paymentDto.setPaymentDate(payment.getPaymentDate());

        paymentDto.setFeeDto(
                payment.getFee() != null ?
                        this.feeMapper.toDto(payment.getFee()) :
                        null
        );

        paymentDto.setSalaryDto(
                payment.getSalary() != null ?
                        this.salaryMapper.toDto(payment.getSalary()) :
                        null
        );

        return paymentDto;
    }
}
