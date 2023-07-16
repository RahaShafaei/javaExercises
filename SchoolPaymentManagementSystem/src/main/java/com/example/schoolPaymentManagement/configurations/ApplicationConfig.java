package com.example.schoolPaymentManagement.configurations;

import com.example.schoolPaymentManagement.controller.paymentObserver.PaymentStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Raha
 * @since 2023-07-10
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public PaymentStatus paymentStatus() {
        return PaymentStatus.getInstance();
    }
}
