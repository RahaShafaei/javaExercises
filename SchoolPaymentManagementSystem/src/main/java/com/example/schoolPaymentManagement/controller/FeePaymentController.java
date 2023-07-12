package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.FeeMapper;
import com.example.schoolPaymentManagement.dto.FeePaymentDto;
import com.example.schoolPaymentManagement.dto.FeePaymentMapper;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.FeePayment;
import com.example.schoolPaymentManagement.repository.InfFeePaymentRepository;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link FeePayment} interactions.
 * </p>
 */
@RestController
public class FeePaymentController {

    private static final String FEE_PAYMENT_ID = "Student id: ";
    private final InfFeePaymentRepository feePaymentRepository;
    private final InfFeeRepository feeRepository;
    private final FeePaymentMapper feePaymentMapper;
    private final FeeMapper feeMapper;

    public FeePaymentController(InfFeePaymentRepository feePaymentRepository,
                                FeePaymentMapper feePaymentMapper,
                                FeeMapper feeMapper,
                                InfFeeRepository feeRepository) {
        this.feePaymentRepository = feePaymentRepository;
        this.feePaymentMapper = feePaymentMapper;
        this.feeRepository = feeRepository;
        this.feeMapper = feeMapper;
    }

    /**
     * <p>Find all {@link FeePayment}s,convert them to {@link FeePaymentDto} and return them.</p>
     *
     * @return list of {@link FeePaymentDto}
     */
    @GetMapping("/feePayments")
    public List<FeePaymentDto> retrieveAllFeePayment() {
        return feePaymentRepository.findAll()
                .stream()
                .map(feePaymentMapper::toDto)
                .toList();
    }

    /**
     * <p>
     * Find a specific {@link FeePayment} according to taken id,
     * convert it to {@link FeePaymentDto} and return it.
     * </p>
     *
     * @param id of {@link FeePayment}
     * @return A {@link FeePaymentDto}
     */
    @GetMapping("/feePayments/{id}")
    public FeePaymentDto retrieveFeePayment(@PathVariable Long id) {
        Optional<FeePayment> feePayment = feePaymentRepository.findById(id);

        if (feePayment.isEmpty())
            throw new ItemNotFoundException(FEE_PAYMENT_ID + id);

        return feePaymentMapper.toDto(feePayment.get());
    }

    /**
     * <p>
     * Find a specific {@link FeePayment} according to taken id,
     * get all of its {@link Fee}, convert them to {@link FeeDto} and return them.
     * </p>
     *
     * @param id of {@link FeePayment}
     * @return list of {@link FeeDto}
     */
    @GetMapping("/feePayments/{id}/fee")
    public FeeDto retrieveFeeOfFeePayment(@PathVariable Long id) {
        Optional<FeePayment> feePayment = feePaymentRepository.findById(id);

        if (feePayment.isEmpty())
            throw new ItemNotFoundException(FEE_PAYMENT_ID + id);

        return feeMapper.toDto(feePayment.get().getFee());
    }

    /**
     * Find a specific {@link FeePayment} according to taken id and delete it.
     *
     * @param id of {@link FeePayment}
     */
    @DeleteMapping("/feePayments/{id}")
    public void deleteFeePayment(@PathVariable Long id) {
        Optional<FeePayment> feePayment = feePaymentRepository.findById(id);

        if (feePayment.isEmpty())
            throw new ItemNotFoundException(FEE_PAYMENT_ID + id);

        feePaymentRepository.deleteById(id);
    }

    /**
     * <p>Create a {@link FeePayment} by use of taken existing {@link Fee} entity id.</p>
     *
     * @param id         of a valid {@link Fee}.
     * @param feePayment object of a valid {@link FeePayment}
     * @return {@link ResponseEntity} of {@link EntityModel} of {@link FeePaymentDto}
     */
    @PostMapping("/feePayments/fee/{id}")
    public ResponseEntity<EntityModel<FeePaymentDto>> createFeePaymentOfFee(
            @PathVariable Long id,
            @Valid @RequestBody FeePayment feePayment
    ) {
        Optional<Fee> fee = feeRepository.findById(id);

        if (fee.isEmpty())
            throw new ItemNotFoundException("Fee id: " + id);

        feePayment.setFee(fee.get());
        FeePayment savedFeePayment = feePaymentRepository.save(feePayment);

        EntityModel<FeePaymentDto> entityModel = EntityModel.of(feePaymentMapper.toDto(savedFeePayment));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveFeePayment(savedFeePayment.getFeePaymentId()));
        entityModel.add(link.withRel("feePayment_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}
