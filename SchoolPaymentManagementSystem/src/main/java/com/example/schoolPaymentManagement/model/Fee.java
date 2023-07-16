package com.example.schoolPaymentManagement.model;

import com.example.schoolPaymentManagement.validator.BigDecimalLength;
import com.example.schoolPaymentManagement.validator.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
                    @Parameter(name = "initial_value", value = "10"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long feeId;

    @BigDecimalLength(minLength = 3)
    private BigDecimal cost;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate deadLine;

    private Boolean status;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Grade grade;

    @OneToOne(mappedBy = "fee")
    private Payment payment;

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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "feeId=" + feeId +
                ", cost=" + cost +
                ", deadLine=" + deadLine +
                ", status=" + status +
                ", student=" + student +
                ", grade=" + grade +
                ", payment=" + payment +
                '}';
    }
}
