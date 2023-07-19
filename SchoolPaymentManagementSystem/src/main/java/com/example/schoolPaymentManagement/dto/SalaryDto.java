package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Salary;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
public class SalaryDto {

    private long salaryId;

    private BigDecimal cost;

    private LocalDate deadLine;

    private GradeDto grade;

    private TeacherDto teacher;

    private LocalDate paymentDate;

}
