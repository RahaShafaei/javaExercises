package com.example.schoolPaymentManagement.controller.paymentObserver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 *      The Observer Pattern used to  to notify students or teachers when their payment status changes.
 *      The Observer pattern promotes loose coupling and allows objects to be notified of changes
 *      without being tightly coupled to each other.
 * </p>
 * <p></p>
 * <p>Consider using the Singleton pattern for managing shared resources or components that
 * need to have a single instance throughout the application. For instance, here a
 * singleton class is used to manage the payment interactions.
 * </p>
 * <p></p>
 * <p>The Initialize-on-demand-holder idiom is a secure way of creating a lazy initialized singleton
 * object in Java.</p>
 * <p></p>
 * <p>The technique is as lazy as possible and works in all known versions of Java. It takes
 * advantage of language guarantees about class initialization, and will therefore work correctly
 * in all Java-compliant compilers and virtual machines.</p>
 * <p></p>
 * <p>The inner class is referenced no earlier (and therefore loaded no earlier by the class loader)
 * than the moment that getInstance() is called. Thus, this solution is thread-safe without
 * requiring special language constructs (i.e. volatile or synchronized).</p>
 */
@Component
public class PaymentStatus implements PaymentStatusObservable {

    /**
     * Singleton instance.
     *
     * @return Singleton instance
     */
    public static PaymentStatus getInstance() {
        return HelperHolder.INSTANCE;
    }

    private List<PaymentStatusObserver> observers;
    private boolean paymentMade;

    public PaymentStatus() {
        observers = new ArrayList<>();
        paymentMade = false;
    }

    public List<PaymentStatusObserver> getObservers() {
        return observers;
    }

    @Override
    public void registerObserver(PaymentStatusObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PaymentStatusObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PaymentStatusObserver observer : observers) {
            observer.updatePaymentStatus(paymentMade);
        }
    }

    // Method to update payment status and notify observers
    public void updatePaymentStatus(boolean paymentMade) {
        this.paymentMade = paymentMade;
        notifyObservers();
    }

    /**
     * Provides the lazy-loaded Singleton instance.
     */
    private static class HelperHolder {
        private static final PaymentStatus INSTANCE = new PaymentStatus();
    }
}