package com.example.schoolPaymentManagement.controller.builder;


import com.example.schoolPaymentManagement.controller.NotifyingController;
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
 * Handle all {@link NotifyingController} constructor parameters.
 * </p>
 */
@Component
public class NotifyingBuilder {

    private final InfSalaryRepository infSalaryRepository;

    private final InfFeeRepository infFeeRepository;

    private final PaymentStatus paymentStatus;

    private NotifyingBuilder(Builder builder) {
        this.infSalaryRepository = builder.infSalaryRepository;
        this.infFeeRepository = builder.infFeeRepository;
        this.paymentStatus = builder.paymentStatus;
    }

    public InfSalaryRepository getInfSalaryRepository() {
        return infSalaryRepository;
    }

    public InfFeeRepository getInfFeeRepository() {
        return infFeeRepository;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfSalaryRepository infSalaryRepository;

        @Autowired
        private InfFeeRepository infFeeRepository;

        @Autowired
        private PaymentStatus paymentStatus;
    }

}
