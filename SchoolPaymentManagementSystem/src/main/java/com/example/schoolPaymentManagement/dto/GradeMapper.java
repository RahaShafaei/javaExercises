package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.controller.builder.GradeBuilder;
import com.example.schoolPaymentManagement.exception.helper.ConvertListToMap;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.model.Teacher;
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

    private final StudentMapper studentMapper;

    private final TeacherMapper teacherMapper;

    public GradeMapper(GradeBuilder gradeBuilder) {
        this.studentMapper = gradeBuilder.getStudentMapper();
        this.teacherMapper = gradeBuilder.getTeacherMapper();
    }

    public GradeDto toDto(Grade grade) {
        GradeDto gradeDto = new GradeDto();

        gradeDto.setGradeId(grade.getGradeId());
        gradeDto.setName(grade.getName());

        if (grade.getStudentList() != null) {
            Map<Long, StudentDto> studentDtl = ConvertListToMap.apply(
                    grade.getStudentList(),
                    Student::getStudentId,
                    studentMapper::toDto
            );
            gradeDto.setStudentDtos(studentDtl);
        }

        if (grade.getTeacherList() != null) {
            Map<Long, TeacherDto> teacherDtl = ConvertListToMap.apply(
                    grade.getTeacherList(),
                    Teacher::getTeacherId,
                    teacherMapper::toDto
            );
            gradeDto.setTeacherDtos(teacherDtl);
        }

        return gradeDto;
    }
}
