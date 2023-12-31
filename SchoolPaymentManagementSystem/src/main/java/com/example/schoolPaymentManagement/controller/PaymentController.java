package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.helper.EnmPaymentTypes;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Payment;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@AllArgsConstructor
@RestController
public class PaymentController {

    PaymentService paymentService;

    /**
     * <p>Find all {@link Payment}s,convert them to {@link PaymentDto} and return them.</p>
     *
     * @return list of {@link PaymentDto}
     */
    @GetMapping("/payments")
    public List<PaymentDto> retrieveAllPayment() {
        return paymentService.getPayments();
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
        return paymentService.getPayment(id);
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
        return paymentService.getPartOfPayment(id, type);
    }

    /**
     * Find a specific {@link Payment} according to taken id and delete it.
     *
     * @param id of {@link Payment}
     */
    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        boolean result = paymentService.deletePayment(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
        PaymentDto paymentDto = paymentService.createPaymentOfSpecificType(payment, type, id);

        EntityModel<PaymentDto> entityModel = EntityModel.of(paymentDto);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePayment(paymentDto.getPaymentId()));
        entityModel.add(link.withRel("payment_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}
