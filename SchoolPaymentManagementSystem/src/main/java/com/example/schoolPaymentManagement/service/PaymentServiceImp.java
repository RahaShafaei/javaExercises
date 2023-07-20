package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.controller.builder.PaymentBuilder;
import com.example.schoolPaymentManagement.controller.paymentFactory.InfFactoryPaymentGeneralities;
import com.example.schoolPaymentManagement.controller.paymentFactory.UseInfFactoryPaymentGeneralities;
import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.dto.PaymentMapper;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.helper.EnmPaymentTypes;
import com.example.schoolPaymentManagement.model.Payment;
import com.example.schoolPaymentManagement.repository.InfPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("paymentService")
public class PaymentServiceImp implements PaymentService {

    private static final String PAYMENT_ID = "Payment id: ";
    private final InfPaymentRepository infPaymentRepository;
    private final PaymentMapper paymentMapper;
    private final UseInfFactoryPaymentGeneralities useInfFactoryPaymentGeneralities;

    public PaymentServiceImp(PaymentBuilder paymentBuilder) {
        this.infPaymentRepository = paymentBuilder.getInfPaymentRepository();
        this.paymentMapper = paymentBuilder.getPaymentMapper();
        this.useInfFactoryPaymentGeneralities = paymentBuilder.getUseInfFactoryPaymentGeneralities();
    }

    @Override
    public List<PaymentDto> getPayments() {
        return infPaymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public PaymentDto getPayment(Long id) {
        Optional<Payment> payment = infPaymentRepository.findById(id);

        if (payment.isEmpty())
            throw new ItemNotFoundException(PAYMENT_ID + id);

        return paymentMapper.toDto(payment.get());
    }

    @Override
    public PaymentDto getPartOfPayment(Long id, String type) {
        InfFactoryPaymentGeneralities creation = useInfFactoryPaymentGeneralities;
        useInfFactoryPaymentGeneralities.setTypeId(id);

        return creation
                .create(EnmPaymentTypes.valueOf(type.toUpperCase()))
                .getPaymentPartsDetails();
    }

    @Override
    public Boolean deletePayment(Long id) {
        Optional<Payment> payment = infPaymentRepository.findById(id);

        if (!payment.isEmpty()) {
            infPaymentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PaymentDto createPaymentOfSpecificType(Payment payment, String type, Long id) {
        InfFactoryPaymentGeneralities creation = useInfFactoryPaymentGeneralities;
        useInfFactoryPaymentGeneralities.setTypeId(id);
        useInfFactoryPaymentGeneralities.setPayment(payment);

        return creation
                .create(EnmPaymentTypes.valueOf(type.toUpperCase()))
                .createPayment();
    }
}
