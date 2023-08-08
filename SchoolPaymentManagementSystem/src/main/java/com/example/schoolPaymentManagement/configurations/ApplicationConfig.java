package com.example.schoolPaymentManagement.configurations;

import com.example.schoolPaymentManagement.controller.paymentObserver.PaymentStatus;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

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

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
