package com.example.schoolpaymentmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "feeId")
@Entity
public class Fee {
    @Id
    @GeneratedValue(generator = "fee_sequence-generator")
    @GenericGenerator(
            name = "fee_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "fee_sequence"),
                    @Parameter(name = "initial_value", value = "8"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long feeId;

    private BigDecimal cost;

    private LocalDate deadLine;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Grade grade;

    @OneToOne(mappedBy = "fee")
    private FeePayment feePayment;

    public Fee() {
    }

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public FeePayment getFeePayment() {
        return feePayment;
    }

    public void setFeePayment(FeePayment feePayment) {
        this.feePayment = feePayment;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "feeId=" + feeId +
                ", cost=" + cost +
                ", deadLine=" + deadLine +
                ", student=" + student +
                ", grade=" + grade +
                ", feePayment=" + feePayment +
                '}';
    }
}
