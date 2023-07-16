package com.example.schoolPaymentManagement.model;

import com.example.schoolPaymentManagement.validator.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "paymentId")
@Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "payment_sequence-generator")
    @GenericGenerator(
            name = "payment_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "payment_sequence"),
                    @Parameter(name = "initial_value", value = "11"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long paymentId;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate paymentDate;

    @OneToOne
    private Fee fee;

    @OneToOne
    private Salary salary;

    public Payment() {
    }

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

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FeePayment{" +
                "paymentId=" + paymentId +
                ", paymentDate=" + paymentDate +
                ", fee=" + fee +
                ", salary=" + salary +
                '}';
    }
}
