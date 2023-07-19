package com.example.schoolPaymentManagement.model;

import com.example.schoolPaymentManagement.validator.BigDecimalLength;
import com.example.schoolPaymentManagement.validator.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
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
                    @Parameter(name = "initial_value", value = "10"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long salaryId;

    @BigDecimalLength(minLength = 3)
    private BigDecimal cost;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate deadLine;

    private Boolean status;

    @ManyToOne
    private Grade grade;

    @ManyToOne
    private Teacher teacher;

    @OneToOne(mappedBy = "salary")
    private Payment payment;

}
