package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.exception.ItemNotIncludedException;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfStudentRepository;
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
 * Handle all {@link Fee} interactions.
 * </p>
 */
@RestController
public class FeeController {
    private static final String FEE_ID = "Fee id: ";
    private final InfFeeRepository feeRepository;
    private final InfStudentRepository studentRepository;
    private final InfGradeRepository gradeRepository;
    private final FeeMapper feeMapper;
    private final StudentMapper studentMapper;
    private final GradeMapper gradeMapper;

    public FeeController(InfFeeRepository feeRepository,
                         FeeMapper feeMapper,
                         InfStudentRepository studentRepository,
                         StudentMapper studentMapper,
                         InfGradeRepository gradeRepository,
                         GradeMapper gradeMapper) {

        this.feeRepository = feeRepository;
        this.feeMapper = feeMapper;

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;

        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    /**
     * <p>Find all {@link Fee}s,convert them to {@link FeeDto} and return them.</p>
     *
     * @return list of {@link FeeDto}
     */
    @GetMapping("/fees")
    public List<FeeDto> retrieveAllFees() {
        return feeRepository.findAll()
                .stream()
                .map(feeMapper::toDto)
                .toList();
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
        Optional<Fee> fee = feeRepository.findById(id);

        if (fee.isEmpty())
            throw new ItemNotFoundException(FEE_ID + id);

        return feeMapper.toDto(fee.get());
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
        Optional<Fee> fee = feeRepository.findById(id);

        if (fee.isEmpty())
            throw new ItemNotFoundException(FEE_ID + id);

        return studentMapper.toDto(fee.get().getStudent());
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
        Optional<Fee> fee = feeRepository.findById(id);

        if (fee.isEmpty())
            throw new ItemNotFoundException(FEE_ID + id);

        return gradeMapper.toDto(fee.get().getGrade());
    }

    /**
     * Find a specific {@link Fee} according to taken id and delete it.
     *
     * @param id of a valid {@link Fee}.
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/fees/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable Long id) {
        Optional<Fee> fee = feeRepository.findById(id);

        if (!fee.isEmpty()) {
            feeRepository.deleteById(id);
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

        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (student.isEmpty() || grade.isEmpty())
            throw new ItemNotFoundException(
                    "Student id: "
                            + studentId
                            + " OR Grade id: "
                            + gradeId
            );

        if (!grade.get().getStudentList().contains(student.get()))
            throw new ItemNotIncludedException(
                    "There isn't any student by id: "
                            + studentId
                            + " in grade by id: "
                            + gradeId
            );

        fee.setStudent(student.get());
        fee.setGrade(grade.get());

        Fee savedFee = feeRepository.save(fee);

        EntityModel<FeeDto> entityModel = EntityModel.of(feeMapper.toDto(savedFee));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveFee(savedFee.getFeeId()));
        entityModel.add(link.withRel("fee_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }
}
