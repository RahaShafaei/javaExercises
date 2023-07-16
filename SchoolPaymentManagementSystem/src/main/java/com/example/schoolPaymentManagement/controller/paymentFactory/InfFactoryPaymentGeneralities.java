package com.example.schoolPaymentManagement.controller.paymentFactory;

import com.example.schoolPaymentManagement.helper.EnmPaymentTypes;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * This is the main interface for implementing FactoryMethod Pattern to
 * create {@link PaymentGeneralities} subclasses.
 * </p>
 */
public interface InfFactoryPaymentGeneralities {
    PaymentGeneralities create(EnmPaymentTypes type);
}
