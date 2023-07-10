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

    private Map<Long, StudentDto> fees;

    private Map<Long, TeacherDto> salaries;

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

    public Map<Long, StudentDto> getFees() {
        return fees;
    }

    public void setFees(Map<Long, StudentDto> fees) {
        this.fees = fees;
    }

    public Map<Long, TeacherDto> getSalaries() {
        return salaries;
    }

    public void setSalaries(Map<Long, TeacherDto> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "GradeDto{" +
                "gradeId=" + gradeId +
                ", name='" + name + '\'' +
                ", fees=" + fees +
                ", salaries=" + salaries +
                '}';
    }
}
