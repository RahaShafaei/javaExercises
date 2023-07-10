package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.FeePayment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To convert {@link FeePayment} to {@link FeePaymentDto}.
 * </p>
 */
@Component
public class FeePaymentMapper {

    public FeePaymentDto toDto(FeePayment feePayment) {
        FeePaymentDto feePaymentDto = new FeePaymentDto();

        feePaymentDto.setFeePaymentId(feePayment.getFeePaymentId());
        feePaymentDto.setPaymentDate(feePayment.getPaymentDate());

        Map<Long, BigDecimal> tempFee = new HashMap<>();
        tempFee.put(feePayment.getFee().getFeeId(), feePayment.getFee().getCost());
        feePaymentDto.setFee(tempFee);

        return feePaymentDto;
    }
}
