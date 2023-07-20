package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.model.*;
import com.example.schoolPaymentManagement.service.GradeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link Grade} interactions.
 * </p>
 */

@AllArgsConstructor
@RestController
public class GradeController {

    private final GradeService gradeService;

    /**
     * <p>Find all {@link Grade}s,convert them to {@link GradeDto} and return them.</p>
     *
     * @return list of {@link GradeDto}
     */
    @GetMapping("/grades")
    public List<GradeDto> retrieveAllGrades() {
        return gradeService.getGrades();
    }

    /**
     * <p>
     * Find a specific {@link Grade} according to taken id,
     * convert it to {@link GradeDto} and return it.
     * </p>
     *
     * @param id of {@link Grade}
     * @return A {@link GradeDto}
     */
    @GetMapping("/grades/{id}")
    public GradeDto retrieveGrade(@PathVariable Long id) {
        return gradeService.getGrade(id);
    }

    /**
     * <p>
     * Find a specific {@link Grade} according to taken id,
     * get all of its {@link Student}, convert them to {@link StudentDto} and return them.
     * </p>
     *
     * @param id of {@link Grade}
     * @return list of {@link StudentDto}
     */
    @GetMapping("/grades/{id}/students")
    public List<StudentDto> retrieveStudentsOfGrade(@PathVariable Long id) {
        return gradeService.getGradeStudents(id);
    }

    /**
     * <p>
     * Find a specific {@link Grade} according to taken id,
     * get all of its {@link Teacher}, convert them to {@link TeacherDto} and return them.
     * </p>
     *
     * @param id of {@link Grade}
     * @return list of {@link TeacherDto}
     */
    @GetMapping("/grades/{id}/teachers")
    public List<TeacherDto> retrieveTeachersOfGrade(@PathVariable Long id) {
        return gradeService.getGradeTeachers(id);
    }

    /**
     * <p>
     * Find a specific {@link Grade} according to taken id,
     * get all of its {@link Fee}, convert them to {@link FeeDto} and return them.
     * </p>
     *
     * @param id of {@link Grade}
     * @return list of {@link FeeDto}
     */
    @GetMapping("/grades/{id}/fees")
    public List<FeeDto> retrieveFeesOfGrade(@PathVariable Long id) {
        return gradeService.getGradeFees(id);
    }

    /**
     * <p>
     * Find a specific {@link Grade} according to taken id,
     * get all of its {@link Salary}, convert them to {@link SalaryDto} and return them.
     * </p>
     *
     * @param id of {@link Grade}
     * @return list of {@link SalaryDto}
     */
    @GetMapping("/grades/{id}/salaries")
    public List<SalaryDto> retrieveSalaryOfGrade(@PathVariable Long id) {
        return gradeService.getSalaries(id);
    }

    /**
     * Find a specific {@link Grade} according to taken id and delete it.
     *
     * @param id of {@link Grade}
     */
    @DeleteMapping("/grades/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        boolean result = gradeService.deleteGrade(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * <p>Create a {@link Grade} </p>
     *
     * @param grade object of {@link Grade}
     * @return {@link ResponseEntity} of {@link Grade}
     */
    @PostMapping("/grades")
    public ResponseEntity<GradeDto> createGrade(@Valid @RequestBody Grade grade) {
        GradeDto savedGrade = gradeService.createGrade(grade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGrade.getGradeId())
                .toUri();

        return ResponseEntity.created(location).body(savedGrade);
    }
}
