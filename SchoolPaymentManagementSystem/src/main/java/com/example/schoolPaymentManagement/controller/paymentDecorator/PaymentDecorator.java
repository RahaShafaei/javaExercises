package com.example.schoolPaymentManagement.controller.paymentDecorator;

/**
 * @author Raha
 * @since 2023-07-10
 * <p>
 * Create the decorator abstract class that implements the {@link InfPayment} interface
 * and holds a reference to the decorated {@link InfPayment} object.
 */
public class PaymentDecorator extends InfPayment {
    protected InfPayment decoratedPayment;

    public PaymentDecorator(InfPayment decoratedPayment) {
        this.decoratedPayment = decoratedPayment;

        if (decoratedPayment.getPayment() != null)
            super.payment = decoratedPayment.getPayment();
    }

    @Override
    public void processPayment() {
        decoratedPayment.processPayment();
    }
}
