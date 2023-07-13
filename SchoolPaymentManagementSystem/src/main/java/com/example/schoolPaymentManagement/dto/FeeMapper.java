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

    private final StudentMapper studentMapper;

    private final GradeMapper gradeMapper;

    private final FeePaymentMapper feePaymentMapper;

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
        feeDto.setStudent(
                fee.getStudent() != null ?
                        this.studentMapper.toDto(fee.getStudent()) :
                        null
        );
        feeDto.setGrade(
                fee.getGrade() != null ?
                        this.gradeMapper.toDto(fee.getGrade()) :
                        null
        );
        feeDto.setFeePayment(
                fee.getFeePayment() != null ?
                        this.feePaymentMapper.toDto(fee.getFeePayment()) :
                        null
        );

        return feeDto;
    }
}
