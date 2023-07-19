package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.controller.PaymentController;
import com.example.schoolPaymentManagement.controller.paymentFactory.UseInfFactoryPaymentGeneralities;
import com.example.schoolPaymentManagement.dto.PaymentMapper;
import com.example.schoolPaymentManagement.repository.InfPaymentRepository;
import lombok.Getter;
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
@Getter
@Component
public class PaymentBuilder {
    private final InfPaymentRepository infPaymentRepository;

    private final PaymentMapper paymentMapper;

    private final UseInfFactoryPaymentGeneralities useInfFactoryPaymentGeneralities;

    private PaymentBuilder(Builder builder) {
        this.infPaymentRepository = builder.infPaymentRepository;
        this.paymentMapper = builder.paymentMapper;
        this.useInfFactoryPaymentGeneralities = builder.useInfFactoryPaymentGeneralities;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfPaymentRepository infPaymentRepository;

        @Autowired
        private PaymentMapper paymentMapper;

        @Autowired
        private UseInfFactoryPaymentGeneralities useInfFactoryPaymentGeneralities;
    }
}
