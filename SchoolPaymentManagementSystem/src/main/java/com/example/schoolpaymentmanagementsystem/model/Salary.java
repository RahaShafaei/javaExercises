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
        property = "salaryId")
@Entity
public class Salary {
    @Id
    @GeneratedValue(generator = "salary_sequence-generator")
    @GenericGenerator(
            name = "salary_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "salary_sequence"),
                    @Parameter(name = "initial_value", value = "8"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long salaryId;

    private BigDecimal cost;

    private LocalDate deadLine;

    @ManyToOne
    private Grade grade;

    @ManyToOne
    private Teacher teacher;

    @OneToOne(mappedBy = "salary")
    private SalaryPayment salaryPayment;

    public Salary() {
    }

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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SalaryPayment getSalaryPayment() {
        return salaryPayment;
    }

    public void setSalaryPayment(SalaryPayment salaryPayment) {
        this.salaryPayment = salaryPayment;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", cost=" + cost +
                ", deadLine=" + deadLine +
                ", grade=" + grade +
                ", teacher=" + teacher +
                ", salaryPayment=" + salaryPayment +
                '}';
    }
}
