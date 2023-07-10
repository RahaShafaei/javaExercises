package com.example.schoolPaymentManagement.dto;

import com.example.schoolPaymentManagement.model.SalaryPayment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To convert {@link SalaryPayment} to {@link SalaryPaymentDto}.
 * </p>
 */
@Component
public class SalaryPaymentMapper {

    public SalaryPaymentDto toDto(SalaryPayment salaryPayment) {
        SalaryPaymentDto salaryPaymentDto = new SalaryPaymentDto();

        salaryPaymentDto.setSalaryPaymentId(salaryPayment.getSalaryPaymentId());
        salaryPaymentDto.setPaymentDate(salaryPayment.getPaymentDate());

        Map<Long, BigDecimal> tempSalary = new HashMap<>();
        tempSalary.put(salaryPayment.getSalary().getSalaryId(), salaryPayment.getSalary().getCost());
        salaryPaymentDto.setSalary(tempSalary);

        return salaryPaymentDto;
    }
}
