package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.FeePayment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link FeePayment} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link FeePayment}.
 * </p>
 */
public class FeePaymentDto {
    private long feePaymentId;

    private LocalDate paymentDate;

    private Map<Long, BigDecimal> fee;

    public long getFeePaymentId() {
        return feePaymentId;
    }

    public void setFeePaymentId(long feePaymentId) {
        this.feePaymentId = feePaymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Map<Long, BigDecimal> getFee() {
        return fee;
    }

    public void setFee(Map<Long, BigDecimal> fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "FeePaymentDto{" +
                "feePaymentId=" + feePaymentId +
                ", paymentDate=" + paymentDate +
                ", fee=" + fee +
                '}';
    }
}
