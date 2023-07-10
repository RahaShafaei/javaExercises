package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.Fee;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To convert {@link Fee} to {@link FeeDto}.
 * </p>
 */
@Component
public class FeeMapper {

    private StudentMapper studentMapper;

    private GradeMapper gradeMapper;

    private FeePaymentMapper feePaymentMapper;

    public FeeMapper(StudentMapper studentMapper,
                     GradeMapper gradeMapper,
                     FeePaymentMapper feePaymentMapper
    ) {
        this.studentMapper = studentMapper;
        this.gradeMapper = gradeMapper;
        this.feePaymentMapper = feePaymentMapper;
    }

    public FeeDto toDto(Fee fee) {
        FeeDto feeDto = new FeeDto();

        feeDto.setFeeId(fee.getFeeId());
        feeDto.setCost(fee.getCost());
        feeDto.setDeadLine(fee.getDeadLine());
        feeDto.setStudent(this.studentMapper.toDto(fee.getStudent()));
        feeDto.setGrade(this.gradeMapper.toDto(fee.getGrade()));
        feeDto.setFeePayment(this.feePaymentMapper.toDto(fee.getFeePayment()));

        return feeDto;
    }
}
