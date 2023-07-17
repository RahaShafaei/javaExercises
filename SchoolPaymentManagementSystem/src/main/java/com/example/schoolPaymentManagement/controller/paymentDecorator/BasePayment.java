package com.example.schoolPaymentManagement.controller.paymentDecorator;

import com.example.schoolPaymentManagement.model.Payment;

/**
 * @author Raha
 * @since 2023-07-10
 * <p>
 * Implement the concrete component class for {@link InfPayment} for Decorator Pattern
 * that performs the base payment processing logic.
 */
public class BasePayment extends InfPayment {
    public BasePayment(Payment payment) {
        super.payment = payment;
    }

    @Override
    public void processPayment() {
        if (super.payment.getFee() != null) {
            super.payment.setCost(super.payment.getFee().getCost());
        } else {
            super.payment.setCost(super.payment.getSalary().getCost());
        }
    }
}
