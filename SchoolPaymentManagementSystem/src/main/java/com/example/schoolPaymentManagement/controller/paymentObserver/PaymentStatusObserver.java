package com.example.schoolPaymentManagement.controller.paymentObserver;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 *     This is the main interface of Observer Pattern to apply desire adjustments.
 * </p>
 */
public interface PaymentStatusObserver {
    void updatePaymentStatus(boolean paymentMade);
}
