package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.SalaryMapper;
import com.example.schoolPaymentManagement.dto.SalaryPaymentDto;
import com.example.schoolPaymentManagement.dto.SalaryPaymentMapper;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.model.SalaryPayment;
import com.example.schoolPaymentManagement.repository.InfSalaryPaymentRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
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
 * Handle all {@link SalaryPayment} interactions.
 * </p>
 */
@RestController
public class SalaryPaymentController {
    private static final String SALARY_PAYMENT_ID = "SalaryPayment id: ";
    private final InfSalaryPaymentRepository salaryPaymentRepository;
    private final InfSalaryRepository salaryRepository;
    private final SalaryPaymentMapper salaryPaymentMapper;
    private final SalaryMapper salaryMapper;

    public SalaryPaymentController(InfSalaryPaymentRepository salaryPaymentRepository,
                                   SalaryPaymentMapper salaryPaymentMapper,
                                   SalaryMapper salaryMapper,
                                   InfSalaryRepository salaryRepository) {
        this.salaryPaymentRepository = salaryPaymentRepository;
        this.salaryPaymentMapper = salaryPaymentMapper;
        this.salaryRepository = salaryRepository;
        this.salaryMapper = salaryMapper;
    }

    /**
     * <p>Find all {@link SalaryPayment}s,convert them to {@link SalaryPaymentDto} and return them.</p>
     *
     * @return list of {@link SalaryPaymentDto}
     */
    @GetMapping("/salaryPayments")
    public List<SalaryPaymentDto> retrieveAllSalaryPayment() {
        return salaryPaymentRepository.findAll()
                .stream()
                .map(salaryPaymentMapper::toDto)
                .toList();
    }

    /**
     * <p>
     * Find a specific {@link SalaryPayment} according to taken id,
     * convert it to {@link SalaryPaymentDto} and return it.
     * </p>
     *
     * @param id of {@link SalaryPayment}
     * @return A {@link SalaryPaymentDto}
     */
    @GetMapping("/salaryPayments/{id}")
    public SalaryPaymentDto retrieveSalaryPayment(@PathVariable Long id) {
        Optional<SalaryPayment> salaryPayment = salaryPaymentRepository.findById(id);

        if (salaryPayment.isEmpty())
            throw new ItemNotFoundException(SALARY_PAYMENT_ID + id);

        return salaryPaymentMapper.toDto(salaryPayment.get());
    }

    /**
     * <p>
     * Find a specific {@link SalaryPayment} according to taken id,
     * get all of its {@link Salary}, convert them to {@link SalaryDto} and return them.
     * </p>
     *
     * @param id of {@link SalaryPayment}
     * @return list of {@link SalaryDto}
     */
    @GetMapping("/salaryPayments/{id}/salary")
    public SalaryDto retrieveSalaryOfSalaryPayment(@PathVariable Long id) {
        Optional<SalaryPayment> salaryPayment = salaryPaymentRepository.findById(id);

        if (salaryPayment.isEmpty())
            throw new ItemNotFoundException(SALARY_PAYMENT_ID + id);

        return salaryMapper.toDto(salaryPayment.get().getSalary());
    }

    /**
     * Find a specific {@link SalaryPayment} according to taken id and delete it.
     *
     * @param id of {@link SalaryPayment}
     */
    @DeleteMapping("/salaryPayments/{id}")
    public void deleteSalaryPayment(@PathVariable Long id) {
        Optional<SalaryPayment> salaryPayment = salaryPaymentRepository.findById(id);

        if (salaryPayment.isEmpty())
            throw new ItemNotFoundException(SALARY_PAYMENT_ID + id);

        salaryPaymentRepository.deleteById(id);
    }

    /**
     * <p>Create a {@link SalaryPayment} by use of taken existing {@link Salary} entity id.</p>
     *
     * @param id            of a valid {@link Salary}.
     * @param salaryPayment object of a valid {@link SalaryPayment}
     * @return {@link ResponseEntity} of {@link EntityModel} of {@link SalaryPaymentDto}
     */
    @PostMapping("/salaryPayments/salary/{id}")
    public ResponseEntity<EntityModel<SalaryPaymentDto>> createSalaryPaymentOfSalary(
            @PathVariable Long id,
            @Valid @RequestBody SalaryPayment salaryPayment
    ) {
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isEmpty())
            throw new ItemNotFoundException("Salary id: " + id);

        salaryPayment.setSalary(salary.get());
        SalaryPayment savedSalaryPayment = salaryPaymentRepository.save(salaryPayment);

        EntityModel<SalaryPaymentDto> entityModel = EntityModel.of(salaryPaymentMapper.toDto(savedSalaryPayment));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveSalaryPayment(savedSalaryPayment.getSalaryPaymentId()));
        entityModel.add(link.withRel("salaryPayment_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}
