package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.controller.builder.NotifyingBuilder;
import com.example.schoolPaymentManagement.controller.paymentObserver.FeeObserver;
import com.example.schoolPaymentManagement.controller.paymentObserver.PaymentStatus;
import com.example.schoolPaymentManagement.controller.paymentObserver.SalaryObserver;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("notifyingService")
public class NotifyingServiceImp implements NotifyingService{
    private final InfSalaryRepository infSalaryRepository;
    private final InfFeeRepository infFeeRepository;
    private final PaymentStatus paymentStatus;

    public NotifyingServiceImp(NotifyingBuilder notifyingBuilder) {
        this.infSalaryRepository = notifyingBuilder.getInfSalaryRepository();
        this.infFeeRepository = notifyingBuilder.getInfFeeRepository();
        this.paymentStatus = notifyingBuilder.getPaymentStatus();
    }

    @Override
    public void notifyingDone() {
        // Create Observable and Observers
        List<Fee> fees = infFeeRepository.findAllByStatusNullAndDeadLineGreaterThanEqualOrPaymentNotNull(LocalDate.now());
        List<Salary> salaries = infSalaryRepository.findAllByStatusNullAndDeadLineGreaterThanEqualOrPaymentNotNull(LocalDate.now());

        notifying(true, fees, salaries);
    }

    @Override
    public void notifyingNotDone() {
        //Create Observable and Observers
        List<Fee> fees = infFeeRepository.findAllByStatusNullAndDeadLineBeforeAndPaymentNull(LocalDate.now());
        List<Salary> salaries = infSalaryRepository.findAllByStatusNullAndDeadLineBeforeAndPaymentNull(LocalDate.now());

        notifying(false, fees, salaries);
    }


    /**
     * A helper method.
     * @param paymentMade {@link Boolean}
     * @param fees {@link List} of {@link Fee}
     * @param salaries {@link List} of {@link Salary}
     */
    private void notifying(Boolean paymentMade, List<Fee> fees, List<Salary> salaries) {
        // Register observers
        fees.stream()
                .map(FeeObserver::new)
                .forEach(f -> {
                    f.setInfFeeRepository(infFeeRepository);
                    paymentStatus.registerObserver(f);
                });

        salaries.stream()
                .map(SalaryObserver::new)
                .forEach(s -> {
                    s.setInfSalaryRepository(infSalaryRepository);
                    paymentStatus.registerObserver(s);
                });

        // Update payment status
        paymentStatus.updatePaymentStatus(paymentMade);
    }
}
