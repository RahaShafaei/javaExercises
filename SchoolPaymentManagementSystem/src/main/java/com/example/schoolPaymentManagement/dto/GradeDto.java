package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Grade;

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
public class GradeDto {

    private long gradeId;

    private String name;

    private Map<Long, FeeDto> fees;

    private Map<Long, SalaryDto> salaries;

    private Map<Long, StudentDto> studentDtos;

    private Map<Long, TeacherDto> teacherDtos;

    public long getGradeId() {
        return gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, FeeDto> getFees() {
        return fees;
    }

    public void setFees(Map<Long, FeeDto> fees) {
        this.fees = fees;
    }

    public Map<Long, SalaryDto> getSalaries() {
        return salaries;
    }

    public void setSalaries(Map<Long, SalaryDto> salaries) {
        this.salaries = salaries;
    }

    public Map<Long, StudentDto> getStudentDtos() {
        return studentDtos;
    }

    public void setStudentDtos(Map<Long, StudentDto> studentDtos) {
        this.studentDtos = studentDtos;
    }

    public Map<Long, TeacherDto> getTeacherDtos() {
        return teacherDtos;
    }

    public void setTeacherDtos(Map<Long, TeacherDto> teacherDtos) {
        this.teacherDtos = teacherDtos;
    }

    @Override
    public String toString() {
        return "GradeDto{" +
                "gradeId=" + gradeId +
                ", name='" + name + '\'' +
                ", fees=" + fees +
                ", salaries=" + salaries +
                ", studentDtos=" + studentDtos +
                ", teacherDtos=" + teacherDtos +
                '}';
    }
}
