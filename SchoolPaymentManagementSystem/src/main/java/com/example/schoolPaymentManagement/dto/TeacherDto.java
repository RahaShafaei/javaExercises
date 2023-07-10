package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Teacher;

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
public class TeacherDto {
    private long teacherId;

    private String firstName;

    private String lastName;

    private Map<Long, BigDecimal> salaries;

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<Long, BigDecimal> getSalaries() {
        return salaries;
    }

    public void setSalaries(Map<Long, BigDecimal> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salaries=" + salaries +
                '}';
    }
}
