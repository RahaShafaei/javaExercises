package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

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
public class PaymentDto {
    private long paymentId;

    private LocalDate paymentDate;
    private BigDecimal cost;

    private FeeDto feeDto;

    private SalaryDto salaryDto;

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public FeeDto getFeeDto() {
        return feeDto;
    }

    public void setFeeDto(FeeDto feeDto) {
        this.feeDto = feeDto;
    }

    public SalaryDto getSalaryDto() {
        return salaryDto;
    }

    public void setSalaryDto(SalaryDto salaryDto) {
        this.salaryDto = salaryDto;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "feePaymentId=" + paymentId +
                ", paymentDate=" + paymentDate +
                ", cost=" + cost +
                ", feeDto=" + feeDto +
                ", salaryDto=" + salaryDto +
                '}';
    }
}
