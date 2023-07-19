package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Teacher;
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
 * Because return {@link Teacher} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Teacher}.
 * </p>
 */
@Getter
@Setter
@ToString
public class TeacherDto {
    private long teacherId;

    private String firstName;

    private String lastName;

    private Map<Long, BigDecimal> salaries;

}
