package com.example.schoolPaymentManagement.controller.paymentFactory;

import com.example.schoolPaymentManagement.dto.FeeMapper;
import com.example.schoolPaymentManagement.dto.PaymentMapper;
import com.example.schoolPaymentManagement.dto.SalaryMapper;
import com.example.schoolPaymentManagement.exception.BadParameterException;
import com.example.schoolPaymentManagement.exception.helper.EnmPaymentTypes;
import com.example.schoolPaymentManagement.model.Payment;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfPaymentRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @see InfFactoryPaymentGeneralities
 *
 * <p>
 * The Factory pattern is useful when you need to create objects of different
 * types based on some conditions or criteria. In a schoolPaymentManagement application,
 * you might have different types of payments (e.g., fee payment,salay payment).
 * The Factory pattern can be used to encapsulate the creation logic and provide
 * a central place to create instances of different payment types.
 * </p>
 * @since 2023-07-10
 */
@Component
public class UseInfFactoryPaymentGeneralities implements InfFactoryPaymentGeneralities {
    private final InfPaymentRepository infPaymentRepository;
    private final InfFeeRepository infFeeRepository;
    private final InfSalaryRepository infSalaryRepository;
    private final PaymentMapper paymentMapper;
    private final FeeMapper feeMapper;
    private final SalaryMapper salaryMapper;

    protected Payment payment;
    protected Long typeId;

    public UseInfFactoryPaymentGeneralities(InfPaymentRepository infPaymentRepository,
                                            InfFeeRepository infFeeRepository,
                                            InfSalaryRepository infSalaryRepository,
                                            PaymentMapper paymentMapper,
                                            FeeMapper feeMapper,
                                            SalaryMapper salaryMapper) {
        this.infPaymentRepository = infPaymentRepository;
        this.infFeeRepository = infFeeRepository;
        this.infSalaryRepository = infSalaryRepository;
        this.paymentMapper = paymentMapper;
        this.feeMapper = feeMapper;
        this.salaryMapper = salaryMapper;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * @param type {@link EnmPaymentTypes}
     *
     * <p>
     * According to {@link EnmPaymentTypes} value create an instance of {@link PaymentGeneralities}'s
     * subclasses.
     * </p>
     */
    @Override
    public PaymentGeneralities create(EnmPaymentTypes type) {

        PaymentGeneralities paymentGeneralities = switch (type) {
            case FEE -> new PaymentFee(this.payment, this.typeId);
            case SALARY -> new PaymentSalary(this.payment, this.typeId);
            default -> throw new BadParameterException("The type is not in defined parameters.");
        };

        paymentGeneralities.setInfPaymentRepository(infPaymentRepository);
        paymentGeneralities.setInfFeeRepository(infFeeRepository);
        paymentGeneralities.setInfSalaryRepository(infSalaryRepository);

        paymentGeneralities.setPaymentMapper(paymentMapper);
        paymentGeneralities.setFeeMapper(feeMapper);
        paymentGeneralities.setSalaryMapper(salaryMapper);

        return paymentGeneralities;
    }
}
