package com.example.schoolPaymentManagement.controller.paymentFactory;

import com.example.schoolPaymentManagement.controller.paymentDecorator.BasePayment;
import com.example.schoolPaymentManagement.controller.paymentDecorator.InfPayment;
import com.example.schoolPaymentManagement.controller.paymentDecorator.TaxDecorator;
import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.exception.ParametersNotValidException;
import com.example.schoolPaymentManagement.model.Payment;
import com.example.schoolPaymentManagement.model.Salary;

import java.util.Optional;

public class PaymentSalary extends PaymentGeneralities {

    public PaymentSalary(Payment payment, Long typeId) {
        super(payment, typeId);
    }

    @Override
    public PaymentDto getPaymentPartsDetails() {
        Optional<Salary> salary = super.infSalaryRepository.findById(super.typeId);

        if (salary.isEmpty())
            throw new ItemNotFoundException("Salary id : " + super.typeId);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setSalaryDto(super.salaryMapper.toDto(salary.get()));
        return paymentDto;
    }

    @Override
    public PaymentDto createPayment() {
        Optional<Salary> salary = super.infSalaryRepository.findById(super.typeId);

        if (salary.isEmpty())
            throw new ItemNotFoundException("Salary id : " + super.typeId);

        if (super.payment.getPaymentDate() != null && super.payment.getPaymentDate().isBefore(salary.get().getDeadLine()))
            throw new ParametersNotValidException("PaymentDate is NULL or PaymentDate is before salary's DeadLine.");

        super.payment.setSalary(salary.get());

        // Create the base InfPayment object
        InfPayment basePayment = new BasePayment(super.payment);
        // Apply decorators
        InfPayment taxDecorator = new TaxDecorator(basePayment);
        // Process payment with decorators
        taxDecorator.processPayment();

        Payment savedPayment = infPaymentRepository.save(super.payment);

        return super.paymentMapper.toDto(savedPayment);
    }
}
