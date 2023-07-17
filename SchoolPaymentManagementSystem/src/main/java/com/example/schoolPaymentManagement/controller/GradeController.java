package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.controller.builder.GradeBuilder;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.*;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link Grade} interactions.
 * </p>
 */
@RestController
public class GradeController {
    private static final String GRADE_ID = "Grade id: ";
    private final InfGradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final FeeMapper feeMapper;
    private final SalaryMapper salaryMapper;

    public GradeController(GradeBuilder gradeBuilder) {
        this.gradeRepository = gradeBuilder.getGradeRepository();
        this.gradeMapper = gradeBuilder.getGradeMapper();
        this.studentMapper = gradeBuilder.getStudentMapper();
        this.teacherMapper = gradeBuilder.getTeacherMapper();
        this.feeMapper = gradeBuilder.getFeeMapper();
        this.salaryMapper = gradeBuilder.getSalaryMapper();
    }

    /**
     * <p>Find all {@link Grade}s,convert them to {@link GradeDto} and return them.</p>
     *
     * @return list of {@link GradeDto}
     */
    @GetMapping("/grades")
    public List<GradeDto> retrieveAllGrades() {
        return gradeRepository.findAll()
                .stream()
                .map(gradeMapper::toDto)
                .toList();
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
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return gradeMapper.toDto(grade.get());
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
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getStudentList()
                .stream()
                .map(studentMapper::toDto)
                .toList();
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
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getTeacherList()
                .stream()
                .map(teacherMapper::toDto)
                .toList();
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
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getFees()
                .stream()
                .map(feeMapper::toDto)
                .toList();
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
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getSalaries()
                .stream()
                .map(salaryMapper::toDto)
                .toList();
    }

    /**
     * Find a specific {@link Grade} according to taken id and delete it.
     *
     * @param id of {@link Grade}
     */
    @DeleteMapping("/grades/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        Optional<Grade> genre = gradeRepository.findById(id);

        if (!genre.isEmpty()) {
            gradeRepository.deleteById(id);
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
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody Grade grade) {
        Grade savedGrade = gradeRepository.save(grade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGrade.getGradeId())
                .toUri();

        return ResponseEntity.created(location).body(savedGrade);
    }
}
