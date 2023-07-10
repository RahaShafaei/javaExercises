package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.helper.Helper;
import com.example.schoolPaymentManagement.model.Salary;
import org.springframework.stereotype.Component;

import java.util.Map;

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
    private GradeMapper grade;

    private TeacherMapper teacher;

    private SalaryPaymentMapper salaryPayment;

    public SalaryMapper(GradeMapper grade,
                        TeacherMapper teacher,
                        SalaryPaymentMapper salaryPayment
    ) {
        this.grade = grade;
        this.teacher = teacher;
        this.salaryPayment = salaryPayment;
    }

    public SalaryDto toDto(Salary salary) {
        SalaryDto salaryDto = new SalaryDto();

        salaryDto.setSalaryId(salary.getSalaryId());
        salaryDto.setCost(salary.getCost());
        salaryDto.setGrade(this.grade.toDto(salary.getGrade()));
        salaryDto.setTeacher(this.teacher.toDto(salary.getTeacher()));
        salaryDto.setSalaryPayment(this.salaryPayment.toDto(salary.getSalaryPayment()));

        return salaryDto;
    }
}
