package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Payment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link Payment} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Payment}.
 * </p>
 */

@Getter
@Setter
@ToString
public class PaymentDto {
    private long paymentId;

    private LocalDate paymentDate;
    private BigDecimal cost;

    private FeeDto feeDto;

    private SalaryDto salaryDto;

}
