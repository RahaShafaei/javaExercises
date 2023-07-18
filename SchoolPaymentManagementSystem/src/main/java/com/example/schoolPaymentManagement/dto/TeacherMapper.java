package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.exception.helper.ConvertListToMap;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.model.Teacher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To convert {@link Teacher} to {@link TeacherDto}.
 * </p>
 */
@Component
public class TeacherMapper {

    public TeacherDto toDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setTeacherId(teacher.getTeacherId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());

        if (teacher.getSalaries() != null) {
            Map<Long, BigDecimal> salaryDtl = ConvertListToMap.apply(
                    teacher.getSalaries(),
                    Salary::getSalaryId,
                    Salary::getCost
            );
            teacherDto.setSalaries(salaryDtl);
        }

        return teacherDto;
    }
}
