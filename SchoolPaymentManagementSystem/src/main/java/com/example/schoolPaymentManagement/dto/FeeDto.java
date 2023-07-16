package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Fee;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link Fee} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Fee}.
 * </p>
 */
public class FeeDto {
    private long feeId;

    private BigDecimal cost;

    private LocalDate deadLine;

    private StudentDto student;

    private GradeDto grade;

    private LocalDate paymentDate;

    public long getFeeId() {
        return feeId;
    }

    public void setFeeId(long feeId) {
        this.feeId = feeId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public GradeDto getGrade() {
        return grade;
    }

    public void setGrade(GradeDto grade) {
        this.grade = grade;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDto(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "FeeDto{" +
                "feeId=" + feeId +
                ", cost=" + cost +
                ", deadLine=" + deadLine +
                ", student=" + student +
                ", grade=" + grade +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
