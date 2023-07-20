package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.service.FeeService;
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
 * Handle all {@link Fee} interactions.
 * </p>
 */
@AllArgsConstructor
@RestController
public class FeeController {

    private final FeeService feeService;

    /**
     * <p>Find all {@link Fee}s,convert them to {@link FeeDto} and return them.</p>
     *
     * @return list of {@link FeeDto}
     */
    @GetMapping("/fees")
    public List<FeeDto> retrieveAllFees() {
        return this.feeService.getFees();
    }

    /**
     * <p>
     * Find a specific {@link Fee} according to taken id,
     * convert it to {@link FeeDto} and return it.
     * </p>
     *
     * @param id of a valid {@link Fee}.
     * @return A {@link FeeDto}
     */
    @GetMapping("/fees/{id}")
    public FeeDto retrieveFee(@PathVariable Long id) {
        return this.feeService.getFee(id);
    }

    /**
     * <p>
     * Find a specific {@link Fee} according to taken id,
     * get its {@link Student}, convert it to {@link StudentDto} and return it.
     * </p>
     *
     * @param id of a valid {@link Student}.
     * @return a {@link StudentDto}
     */
    @GetMapping("/fees/{id}/student")
    public StudentDto retrieveStudentOfFee(@PathVariable Long id) {
        return this.feeService.getFeeStudent(id);
    }

    /**
     * <p>
     * Find a specific {@link Fee} according to taken id,
     * get its {@link Grade}, convert it to {@link GradeDto} and return it.
     * </p>
     *
     * @param id of a valid {@link Grade}.
     * @return a {@link GradeDto}.
     */
    @GetMapping("/fees/{id}/grade")
    public GradeDto retrieveGradeOfFee(@PathVariable Long id) {
        return this.feeService.getFeeGrade(id);
    }

    /**
     * Find a specific {@link Fee} according to taken id and delete it.
     *
     * @param id of a valid {@link Fee}.
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/fees/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable Long id) {
        boolean result = this.feeService.deleteFee(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * <p>Create a {@link Fee} </p>
     *
     * @param fee       a {@link Fee} object.
     * @param studentId an id of a valid {@link Student}
     * @param gradeId   an id of a valid {@link Grade}
     * @return {@link ResponseEntity} of {@link Fee}
     */
    @PostMapping("/fees/student/{studentId}/grade/{gradeId}")
    public ResponseEntity<EntityModel<FeeDto>> createFee(@Valid @RequestBody Fee fee,
                                                         @PathVariable Long studentId,
                                                         @PathVariable Long gradeId) {

        FeeDto savedFee = this.feeService.createFee(fee, studentId, gradeId);

        EntityModel<FeeDto> entityModel = EntityModel.of(savedFee);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveFee(savedFee.getFeeId()));
        entityModel.add(link.withRel("fee_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }
}
