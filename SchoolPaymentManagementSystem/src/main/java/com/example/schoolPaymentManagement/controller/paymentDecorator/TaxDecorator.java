package com.example.schoolPaymentManagement.controller.paymentDecorator;

import java.math.BigDecimal;

/**
 * @author Raha
 * @since 2023-07-10
 * <p>
 * The TaxDecorator class extend the {@link PaymentDecorator} and add additional
 * functionality by applying taxes.
 */
public class TaxDecorator extends PaymentDecorator {
    public TaxDecorator(InfPayment decoratedPayment) {
        super(decoratedPayment);
    }

    @Override
    public void processPayment() {
        super.processPayment();
        applyTax();
    }

    private void applyTax() {
        BigDecimal tempCost = super.decoratedPayment.getPayment().getCost().multiply(BigDecimal.valueOf(0.02));
        tempCost = super.decoratedPayment.getPayment().getCost().add(tempCost);
        super.decoratedPayment.getPayment().setCost(tempCost);
    }
}
