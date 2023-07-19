package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Fee;
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
 * Because return {@link Fee} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Fee}.
 * </p>
 */

@Getter
@Setter
@ToString
public class FeeDto {
    private long feeId;

    private BigDecimal cost;

    private LocalDate deadLine;

    private StudentDto student;

    private GradeDto grade;

    private LocalDate paymentDate;

}
