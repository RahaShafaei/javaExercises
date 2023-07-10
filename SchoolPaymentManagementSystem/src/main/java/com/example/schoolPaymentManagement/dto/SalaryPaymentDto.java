package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.SalaryPayment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link SalaryPayment} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link SalaryPayment}.
 * </p>
 */
public class SalaryPaymentDto {
    private long salaryPaymentId;

    private LocalDate paymentDate;

    private Map<Long, BigDecimal> salary;

    public long getSalaryPaymentId() {
        return salaryPaymentId;
    }

    public void setSalaryPaymentId(long salaryPaymentId) {
        this.salaryPaymentId = salaryPaymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Map<Long, BigDecimal> getSalary() {
        return salary;
    }

    public void setSalary(Map<Long, BigDecimal> salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "SalaryPaymentDto{" +
                "salaryPaymentId=" + salaryPaymentId +
                ", paymentDate=" + paymentDate +
                ", salary=" + salary +
                '}';
    }
}
