package com.example.schoolPaymentManagement.controller.paymentFactory;

import com.example.schoolPaymentManagement.controller.paymentDecorator.BasePayment;
import com.example.schoolPaymentManagement.controller.paymentDecorator.InfPayment;
import com.example.schoolPaymentManagement.controller.paymentDecorator.LateFeeDecorator;
import com.example.schoolPaymentManagement.controller.paymentDecorator.TaxDecorator;
import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.exception.ParametersNotValidException;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Payment;

import java.util.Optional;

public class PaymentFee extends PaymentGeneralities {

    public PaymentFee(Payment payment, Long typeId) {
        super(payment, typeId);
    }

    @Override
    public PaymentDto getPaymentPartsDetails() {
        Optional<Fee> fee = super.infFeeRepository.findById(super.typeId);

        if (fee.isEmpty())
            throw new ItemNotFoundException("Fee id : " + super.typeId);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setFeeDto(super.feeMapper.toDto(fee.get()));
        return paymentDto;
    }

    @Override
    public PaymentDto createPayment() {
        Optional<Fee> fee = super.infFeeRepository.findById(super.typeId);

        if (fee.isEmpty())
            throw new ItemNotFoundException("Fee id: " + super.typeId);

        if (super.payment.getPaymentDate() != null && super.payment.getPaymentDate().isBefore(fee.get().getDeadLine()))
            throw new ParametersNotValidException("PaymentDate is NULL or PaymentDate is before Fee's DeadLine.");

        super.payment.setFee(fee.get());

        // Create the base object
        InfPayment basePayment = new BasePayment(super.payment);
        // Apply decorators
        InfPayment lateFeeDecorator = new LateFeeDecorator(basePayment);
        InfPayment taxDecorator = new TaxDecorator(lateFeeDecorator);
        // Process payment with decorators
        taxDecorator.processPayment();

        Payment savedPayment = infPaymentRepository.save(super.payment);

        return super.paymentMapper.toDto(savedPayment);
    }
}
