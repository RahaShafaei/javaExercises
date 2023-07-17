package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.controller.PaymentController;
import com.example.schoolPaymentManagement.controller.paymentFactory.UseInfFactoryPaymentGeneralities;
import com.example.schoolPaymentManagement.controller.paymentObserver.PaymentStatus;
import com.example.schoolPaymentManagement.dto.PaymentMapper;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfPaymentRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link PaymentController} constructor parameters.
 * </p>
 */
@Component
public class PaymentBuilder {
    private final InfPaymentRepository infPaymentRepository;

    private final InfSalaryRepository infSalaryRepository;

    private final InfFeeRepository infFeeRepository;

    private final PaymentMapper paymentMapper;

    private final UseInfFactoryPaymentGeneralities useInfFactoryPaymentGeneralities;

    private final PaymentStatus paymentStatus;

    private PaymentBuilder(Builder builder) {
        this.infPaymentRepository = builder.infPaymentRepository;
        this.infSalaryRepository = builder.infSalaryRepository;
        this.infFeeRepository = builder.infFeeRepository;
        this.paymentMapper = builder.paymentMapper;
        this.useInfFactoryPaymentGeneralities = builder.useInfFactoryPaymentGeneralities;
        this.paymentStatus = builder.paymentStatus;
    }

    public InfPaymentRepository getInfPaymentRepository() {
        return infPaymentRepository;
    }

    public InfSalaryRepository getInfSalaryRepository() {
        return infSalaryRepository;
    }

    public InfFeeRepository getInfFeeRepository() {
        return infFeeRepository;
    }

    public PaymentMapper getPaymentMapper() {
        return paymentMapper;
    }

    public UseInfFactoryPaymentGeneralities getUseInfFactoryPaymentGeneralities() {
        return useInfFactoryPaymentGeneralities;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfPaymentRepository infPaymentRepository;

        @Autowired
        private InfSalaryRepository infSalaryRepository;

        @Autowired
        private InfFeeRepository infFeeRepository;

        @Autowired
        private PaymentMapper paymentMapper;

        @Autowired
        private UseInfFactoryPaymentGeneralities useInfFactoryPaymentGeneralities;

        @Autowired
        private PaymentStatus paymentStatus;
    }
}
