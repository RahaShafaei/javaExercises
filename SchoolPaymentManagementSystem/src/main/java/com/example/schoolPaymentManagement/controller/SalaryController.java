package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.model.Teacher;
import com.example.schoolPaymentManagement.service.SalaryService;
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
 * Handle all {@link Salary} interactions.
 * </p>
 */
@AllArgsConstructor
@RestController
public class SalaryController {
    SalaryService salaryService;

    /**
     * <p>Find all {@link Salary}s,convert them to {@link SalaryDto} and return them.</p>
     *
     * @return list of {@link SalaryDto}
     */
    @GetMapping("/salaries")
    public List<SalaryDto> retrieveAllSalaries() {
        return salaryService.getSalaries();
    }

    /**
     * <p>
     * Find a specific {@link Salary} according to taken id,
     * convert it to {@link SalaryDto} and return it.
     * </p>
     *
     * @param id of {@link Salary}
     * @return A {@link SalaryDto}
     */
    @GetMapping("/salaries/{id}")
    public SalaryDto retrieveSalary(@PathVariable Long id) {
        return salaryService.getSalary(id);
    }

    /**
     * <p>
     * Find a specific {@link Salary} according to taken id,
     * get its {@link Teacher}, convert it to {@link TeacherDto} and return it.
     * </p>
     *
     * @param id of {@link Salary}
     * @return a {@link TeacherDto}
     */
    @GetMapping("/salaries/{id}/teacher")
    public TeacherDto retrieveTeacherOfSalary(@PathVariable Long id) {
        return salaryService.getSalaryTeacher(id);
    }

    /**
     * <p>
     * Find a specific {@link Salary} according to taken id,
     * get its {@link Grade}, convert it to {@link GradeDto} and return it.
     * </p>
     *
     * @param id of {@link Salary}
     * @return list of {@link GradeDto}
     */
    @GetMapping("/salaries/{id}/grade")
    public GradeDto retrieveGradeOfSalary(@PathVariable Long id) {
        return salaryService.getSalaryGrade(id);
    }

    /**
     * Find a specific {@link Salary} according to taken id and delete it.
     *
     * @param id of {@link Salary}
     */
    @DeleteMapping("/salaries/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        boolean result = salaryService.deleteSalary(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * <p>Create a {@link Salary} </p>
     *
     * @param salary    object of {@link Salary}
     * @param teacherId an id of a valid {@link Teacher}
     * @param gradeId   an id of a valid {@link Grade}
     * @return {@link ResponseEntity} of {@link Salary}
     */
    @PostMapping("/salaries/teacher/{teacherId}/grade/{gradeId}")
    public ResponseEntity<EntityModel<SalaryDto>> createSalary(@Valid @RequestBody Salary salary,
                                                               @PathVariable Long teacherId,
                                                               @PathVariable Long gradeId) {

        SalaryDto savedSalary = salaryService.createSalary(salary, teacherId, gradeId);

        EntityModel<SalaryDto> entityModel = EntityModel.of(savedSalary);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveSalary(savedSalary.getSalaryId()));
        entityModel.add(link.withRel("salary_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }
}
