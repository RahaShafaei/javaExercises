package com.example.schoolPaymentManagement.model;

import com.example.schoolPaymentManagement.validator.BigDecimalLength;
import com.example.schoolPaymentManagement.validator.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @BigDecimalLength(minLength = 3)
    private BigDecimal cost;

    @OneToOne
    private Fee fee;

    @OneToOne
    private Salary salary;

}
