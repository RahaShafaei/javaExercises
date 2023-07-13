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
        property = "feePaymentId")
@Entity
public class FeePayment {
    @Id
    @GeneratedValue(generator = "feePayment_sequence-generator")
    @GenericGenerator(
            name = "feePayment_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "feePayment_sequence"),
                    @Parameter(name = "initial_value", value = "6"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long feePaymentId;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate paymentDate;

    @OneToOne
    private Fee fee;

    public FeePayment() {
    }

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

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "FeePayment{" +
                "feePaymentId=" + feePaymentId +
                ", paymentDate=" + paymentDate +
                ", fee=" + fee +
                '}';
    }
}
