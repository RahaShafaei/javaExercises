package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Grade;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link Grade} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Grade}.
 * </p>
 */

@Getter
@Setter
@ToString
public class GradeDto {

    private long gradeId;

    private String name;

    private Map<Long, FeeDto> fees;

    private Map<Long, SalaryDto> salaries;

    private Map<Long, StudentDto> studentDtos;

    private Map<Long, TeacherDto> teacherDtos;

}
