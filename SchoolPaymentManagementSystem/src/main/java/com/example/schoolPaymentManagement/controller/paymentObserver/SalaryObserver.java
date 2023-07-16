package com.example.schoolPaymentManagement.controller.paymentObserver;

import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 *     This is one of {@link PaymentStatusObserver} subclasses to implement Observer Pattern's
 *     settings for {@link Salary}s.
 * </p>
 */
public class SalaryObserver implements PaymentStatusObserver{
    private static final Logger logger;

    private InfSalaryRepository infSalaryRepository;

    static {
        logger = LoggerFactory.getLogger(SalaryObserver.class);
    }
    private Salary salary;

    public SalaryObserver(Salary salary) {
        this.salary = salary;
    }

    public InfSalaryRepository getInfSalaryRepository() {
        return infSalaryRepository;
    }

    public void setInfSalaryRepository(InfSalaryRepository infSalaryRepository) {
        this.infSalaryRepository = infSalaryRepository;
    }

    @Override
    public void updatePaymentStatus(boolean paymentMade) {
        this.salary.setStatus(paymentMade);
        this.infSalaryRepository.save(this.salary);

        if (paymentMade) {
            logger.info("Notification to Teacher '{} {}': Your salary has been paid.",
                    salary.getTeacher().getFirstName(),
                    salary.getTeacher().getLastName());
        } else {
            logger.info("Notification to Teacher '{} {}': Your salary is pending.",
                    salary.getTeacher().getFirstName(),
                    salary.getTeacher().getLastName());
        }
    }

    @Override
    public String toString() {
        return "SalaryObserver{" +
                "salary=" + salary +
                '}';
    }
}
