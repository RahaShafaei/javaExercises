package com.example.schoolPaymentManagement.controller.paymentDecorator;

import com.example.schoolPaymentManagement.model.Payment;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * The base component interface for Decorator Pattern.
 */
public abstract class InfPayment {
    protected Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public abstract void processPayment();
}
