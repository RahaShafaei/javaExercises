package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.controller.builder.PaymentBuilder;
import com.example.schoolPaymentManagement.controller.paymentFactory.InfFactoryPaymentGeneralities;
import com.example.schoolPaymentManagement.controller.paymentFactory.UseInfFactoryPaymentGeneralities;
import com.example.schoolPaymentManagement.controller.paymentObserver.FeeObserver;
import com.example.schoolPaymentManagement.controller.paymentObserver.PaymentStatus;
import com.example.schoolPaymentManagement.controller.paymentObserver.SalaryObserver;
import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.dto.PaymentMapper;
import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.helper.EnmPaymentTypes;
import com.example.schoolPaymentManagement.model.*;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfPaymentRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link Payment} interactions.
 * </p>
 */
@RestController
public class PaymentController {

    private static final String PAYMENT_ID = "Payment id: ";
    private final InfPaymentRepository infPaymentRepository;
    private final InfSalaryRepository infSalaryRepository;
    private final InfFeeRepository infFeeRepository;
    private final PaymentMapper paymentMapper;
    private final UseInfFactoryPaymentGeneralities useInfFactoryPaymentGeneralities;
    private final PaymentStatus paymentStatus;

    public PaymentController(PaymentBuilder paymentBuilder) {
        this.infPaymentRepository = paymentBuilder.getInfPaymentRepository();
        this.infSalaryRepository = paymentBuilder.getInfSalaryRepository();
        this.infFeeRepository = paymentBuilder.getInfFeeRepository();
        this.paymentMapper = paymentBuilder.getPaymentMapper();
        this.useInfFactoryPaymentGeneralities = paymentBuilder.getUseInfFactoryPaymentGeneralities();
        this.paymentStatus = paymentBuilder.getPaymentStatus();
    }

    /**
     * <p>Find all {@link Payment}s,convert them to {@link PaymentDto} and return them.</p>
     *
     * @return list of {@link PaymentDto}
     */
    @GetMapping("/payments")
    public List<PaymentDto> retrieveAllPayment() {
        return infPaymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    /**
     * <p>
     * Find a specific {@link Payment} according to taken id,
     * convert it to {@link PaymentDto} and return it.
     * </p>
     *
     * @param id of {@link Payment}
     * @return A {@link PaymentDto}
     */
    @GetMapping("/payments/{id}")
    public PaymentDto retrievePayment(@PathVariable Long id) {
        Optional<Payment> payment = infPaymentRepository.findById(id);

        if (payment.isEmpty())
            throw new ItemNotFoundException(PAYMENT_ID + id);

        return paymentMapper.toDto(payment.get());
    }

    /**
     * <p>
     * Find a specific {@link Payment} according to taken id,
     * get its {@link Fee} or {@link Salary}, convert it to {@link FeeDto} or
     * {@link SalaryDto} respectively and return it.
     * </p>
     *
     * @param id   of {@link Payment}
     * @param type a string to specify type of returning {@link PaymentDto}s.
     *             should be one of these values {@link EnmPaymentTypes#FEE} or
     *             {@link EnmPaymentTypes#SALARY}.
     * @return {@link PaymentDto}
     */
    @GetMapping("/payments/{type}/{id}")
    public PaymentDto retrievePartOfPayment(@PathVariable Long id,
                                            @PathVariable String type
    ) {
        InfFactoryPaymentGeneralities creation = useInfFactoryPaymentGeneralities;
        useInfFactoryPaymentGeneralities.setTypeId(id);

        return creation
                .create(EnmPaymentTypes.valueOf(type.toUpperCase()))
                .getPaymentPartsDetails();
    }

    /**
     * Find a specific {@link Payment} according to taken id and delete it.
     *
     * @param id of {@link Payment}
     */
    @DeleteMapping("/payments/{id}")
    public void deletePayment(@PathVariable Long id) {
        Optional<Payment> payment = infPaymentRepository.findById(id);

        if (payment.isEmpty())
            throw new ItemNotFoundException(PAYMENT_ID + id);

        infPaymentRepository.deleteById(id);
    }

    /**
     * <p>Create a {@link Payment} by use of taken existing {@link Fee} or {@link Salary} entity id.</p>
     *
     * @param id      of a valid {@link Fee} or {@link Salary}.
     * @param type    a string to specify type of returning {@link PaymentDto}s.
     *                should be one of these values {@link EnmPaymentTypes#FEE} or
     *                {@link EnmPaymentTypes#SALARY}.
     * @param payment object of a valid {@link Payment}
     * @return {@link ResponseEntity} of {@link EntityModel} of {@link PaymentDto}
     */
    @PostMapping("/payments/{type}/{id}")
    public ResponseEntity<EntityModel<PaymentDto>> createPaymentOfSpecificType(
            @PathVariable Long id,
            @PathVariable String type,
            @Valid @RequestBody Payment payment) {
        InfFactoryPaymentGeneralities creation = useInfFactoryPaymentGeneralities;
        useInfFactoryPaymentGeneralities.setTypeId(id);
        useInfFactoryPaymentGeneralities.setPayment(payment);

        PaymentDto paymentDto = creation
                .create(EnmPaymentTypes.valueOf(type.toUpperCase()))
                .createPayment();

        EntityModel<PaymentDto> entityModel = EntityModel.of(paymentDto);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePayment(paymentDto.getPaymentId()));
        entityModel.add(link.withRel("payment_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

    // Observers Notifications ================================

    /**
     * Notifying {@link Student}s or {@link Teacher}s that their payment has been made.
     */
    @GetMapping("/payments/notifying/done")
    public void notifyingDone() {
//         Create Observable and Observers
        List<Fee> fees = infFeeRepository.findAllByStatusNullAndDeadLineGreaterThanEqualOrPaymentNotNull(LocalDate.now());
        List<Salary> salaries = infSalaryRepository.findAllByStatusNullAndDeadLineGreaterThanEqualOrPaymentNotNull(LocalDate.now());

        notifying(true, fees, salaries);
    }

    /**
     * Notifying {@link Student}s or {@link Teacher}s that their payment has not been made.
     */
    @GetMapping("/payments/notifying/notDone")
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
