package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Student;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Because return {@link Student} as a response object
 * caused "StackOverflowError", this class created to handle response objects
 * for {@link Student}.
 * </p>
 */
public class StudentDto {
    private long studentId;

    private String firstName;

    private String lastName;

    private Map<Long, BigDecimal> fees;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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

    public Map<Long, BigDecimal> getFees() {
        return fees;
    }

    public void setFees(Map<Long, BigDecimal> fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fees=" + fees +
                '}';
    }
}
