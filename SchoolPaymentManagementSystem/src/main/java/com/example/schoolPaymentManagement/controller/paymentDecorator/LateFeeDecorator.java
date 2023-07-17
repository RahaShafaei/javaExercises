package com.example.schoolPaymentManagement.controller.paymentDecorator;

import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Raha
 * @since 2023-07-10
 * <p>
 * The LateFeeDecorator class extend the {@link PaymentDecorator} and add additional
 * functionality by applying late fees.
 */
public class LateFeeDecorator extends PaymentDecorator {
    public LateFeeDecorator(InfPayment decoratedPayment) {
        super(decoratedPayment);
    }

    @Override
    public void processPayment() {
        super.processPayment();
        applyLateFee();
    }

    private void applyLateFee() {
        long daysBetween = DAYS.between(
                super.decoratedPayment.getPayment().getFee().getDeadLine(),
                super.decoratedPayment.getPayment().getPaymentDate()
        );

        if (daysBetween > 0) {
            BigDecimal tempCost = BigDecimal.valueOf(daysBetween).multiply(BigDecimal.valueOf(10));
            tempCost = super.decoratedPayment.getPayment().getCost().add(tempCost);
            super.decoratedPayment.getPayment().setCost(tempCost);
        }
    }
}
