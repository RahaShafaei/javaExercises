package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.controller.builder.SalaryBuilder;
import com.example.schoolPaymentManagement.model.Salary;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 *     To convert {@link Salary} to {@link SalaryDto}.
 * </p>
 */
@Component
public class SalaryMapper {
    private final GradeMapper gradeMapper;

    private final TeacherMapper teacherMapper;

    public SalaryMapper(SalaryBuilder salaryBuilder) {
        this.gradeMapper = salaryBuilder.getGradeMapper();
        this.teacherMapper = salaryBuilder.getTeacherMapper();
    }

    public SalaryDto toDto(Salary salary) {
        SalaryDto salaryDto = new SalaryDto();

        salaryDto.setSalaryId(salary.getSalaryId());
        salaryDto.setCost(salary.getCost());
        salaryDto.setGrade(
                salary.getGrade() != null ?
                        this.gradeMapper.toDto(salary.getGrade()) :
                        null
        );
        salaryDto.setTeacher(
                salary.getTeacher() != null ?
                        this.teacherMapper.toDto(salary.getTeacher()) :
                        null
        );
        salaryDto.setPaymentDate(
                salary.getPayment() != null ?
                        salary.getPayment().getPaymentDate() :
                        null
        );

        return salaryDto;
    }
}
