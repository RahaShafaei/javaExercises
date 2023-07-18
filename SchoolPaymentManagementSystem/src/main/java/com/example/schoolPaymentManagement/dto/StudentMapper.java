package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.exception.helper.ConvertListToMap;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Student;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To convert {@link Student} to {@link StudentDto}.
 * </p>
 */
@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();

        studentDto.setStudentId(student.getStudentId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());

        if (student.getFees() != null) {
            Map<Long, BigDecimal> feeDtl = ConvertListToMap.apply(
                    student.getFees(),
                    Fee::getFeeId,
                    Fee::getCost
            );
            studentDto.setFees(feeDtl);
        }

        return studentDto;
    }
}
