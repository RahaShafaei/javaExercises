package com.example.schoolPaymentManagement.controller.paymentObserver;

/**
 * @author Raha
 * @since 2023-07-10
 */
public interface PaymentStatusObservable {
    void registerObserver(PaymentStatusObserver observer);

    void removeObserver(PaymentStatusObserver observer);

    void notifyObservers();
}
