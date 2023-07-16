package com.example.schoolPaymentManagement.controller.paymentObserver;

import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 *     This is one of {@link PaymentStatusObserver} subclasses to implement Observer Pattern's
 *     settings for {@link Fee}s.
 * </p>
 */
public class FeeObserver implements PaymentStatusObserver{
    private static final Logger logger;

    private InfFeeRepository infFeeRepository;

    static {
        logger = LoggerFactory.getLogger(FeeObserver.class);
    }
    private Fee fee;

    public FeeObserver(Fee fee) {
        this.fee = fee;
    }

    public InfFeeRepository getInfFeeRepository() {
        return infFeeRepository;
    }

    public void setInfFeeRepository(InfFeeRepository infFeeRepository) {
        this.infFeeRepository = infFeeRepository;
    }

    @Override
    public void updatePaymentStatus(boolean paymentMade) {
        this.fee.setStatus(paymentMade);
        infFeeRepository.save(this.fee);

        if (paymentMade) {
            logger.info("Notification to Student '{} {}': Your payment is complete.",
                    fee.getStudent().getFirstName(),
                    fee.getStudent().getLastName());
        } else {
            logger.info("Notification to Student '{} {}': Your payment is overdue.",
                    fee.getStudent().getFirstName(),
                    fee.getStudent().getLastName());
        }
    }

    @Override
    public String toString() {
        return "FeeObserver{" +
                "fee=" + fee +
                '}';
    }
}
