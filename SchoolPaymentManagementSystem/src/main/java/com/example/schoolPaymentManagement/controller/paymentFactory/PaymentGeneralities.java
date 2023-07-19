package com.example.schoolPaymentManagement.controller.paymentFactory;

import com.example.schoolPaymentManagement.dto.FeeMapper;
import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.dto.PaymentMapper;
import com.example.schoolPaymentManagement.dto.SalaryMapper;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Payment;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfPaymentRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * This is the main class of FactoryMethod Pattern to provide generalities
 * of {@link Payment}.
 * </p>
 * <p></p>
 * <p>
 * Because it was necessary to get an instance of this class, it was not possible
 * to add a Spring annotation for this class to use IOC.
 * </p>
 */
@Getter
@Setter
public abstract class PaymentGeneralities {
    protected InfPaymentRepository infPaymentRepository;

    protected InfFeeRepository infFeeRepository;

    protected InfSalaryRepository infSalaryRepository;

    protected PaymentMapper paymentMapper;

    protected FeeMapper feeMapper;

    protected SalaryMapper salaryMapper;

    protected Payment payment;
    protected Long typeId;

    public PaymentGeneralities(Payment payment, Long typeId) {
        this.payment = payment;
        this.typeId = typeId;
    }

    /**
     * <p>
     * Get {@link Payment} details based on the requested type
     * {@link Fee} or {@link Salary}.
     * </p>
     *
     * @return List of {@link PaymentDto}
     */
    public abstract PaymentDto getPaymentPartsDetails();

    /**
     * <p>
     * Create a {@link Payment} based on the requested type
     * {@link Fee} or {@link Salary}.
     * </p>
     *
     * @return List of {@link PaymentDto}
     */
    public abstract PaymentDto createPayment();
}
