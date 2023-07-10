package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Salary;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link Salary} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Salary}.
 * </p>
 */
public class SalaryDto {

    private long salaryId;

    private BigDecimal cost;

    private LocalDate deadLine;

    private GradeDto grade;

    private TeacherDto teacher;

    private SalaryPaymentDto salaryPayment;

    public long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(long salaryId) {
        this.salaryId = salaryId;
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

    public GradeDto getGrade() {
        return grade;
    }

    public void setGrade(GradeDto grade) {
        this.grade = grade;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public SalaryPaymentDto getSalaryPayment() {
        return salaryPayment;
    }

    public void setSalaryPayment(SalaryPaymentDto salaryPayment) {
        this.salaryPayment = salaryPayment;
    }

    @Override
    public String toString() {
        return "SalaryDto{" +
                "salaryId=" + salaryId +
                ", cost=" + cost +
                ", deadLine=" + deadLine +
                ", grade=" + grade +
                ", teacher=" + teacher +
                ", salaryPayment=" + salaryPayment +
                '}';
    }
}
