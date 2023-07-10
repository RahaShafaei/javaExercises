package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.helper.Helper;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Salary;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To convert {@link Grade} to {@link GradeDto}.
 * </p>
 */
@Component
public class GradeMapper {

    private StudentMapper studentMapper;

    private TeacherMapper teacherMapper;

    public GradeMapper(StudentMapper studentMapper,
                       TeacherMapper teacherMapper
    ) {
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
    }

    public GradeDto toDto(Grade grade) {
        GradeDto gradeDto = new GradeDto();

        gradeDto.setGradeId(grade.getGradeId());
        gradeDto.setName(grade.getName());

        if (grade.getFees() != null) {
            Map<Long, StudentDto> feeDtl = Helper.convertListToMapAccordingToMapper(
                    grade.getFees(),
                    Fee::getFeeId,
                    Fee::getStudent,
                    studentMapper::toDto
            );
            gradeDto.setFees(feeDtl);
        }

        if (grade.getSalaries() != null) {
            Map<Long, TeacherDto> salaryDtl = Helper.convertListToMapAccordingToMapper(
                    grade.getSalaries(),
                    Salary::getSalaryId,
                    Salary::getTeacher,
                    teacherMapper::toDto
            );
            gradeDto.setSalaries(salaryDtl);
        }

        return gradeDto;
    }
}
