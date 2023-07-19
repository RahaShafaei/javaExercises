package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link Student} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Student}.
 * </p>
 */
@Getter
@Setter
@ToString
public class StudentDto {
    private long studentId;

    private String firstName;

    private String lastName;

    private Map<Long, BigDecimal> fees;

}
