package com.example.schoolPaymentManagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "salaryPaymentId")
@Entity
public class SalaryPayment {
    @Id
    @GeneratedValue(generator = "salaryPayment_sequence-generator")
    @GenericGenerator(
            name = "salaryPayment_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "salaryPayment_sequence"),
                    @Parameter(name = "initial_value", value = "6"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long salaryPaymentId;

    private LocalDate paymentDate;

    @OneToOne
    private Salary salary;

    public SalaryPayment() {
    }

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

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "SalaryPayment{" +
                "salaryPaymentId=" + salaryPaymentId +
                ", paymentDate=" + paymentDate +
                ", salary=" + salary +
                '}';
    }
}
