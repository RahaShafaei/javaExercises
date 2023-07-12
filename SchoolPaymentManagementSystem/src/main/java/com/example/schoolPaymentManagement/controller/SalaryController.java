package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.model.Teacher;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import com.example.schoolPaymentManagement.repository.InfTeacherRepository;
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
 * Handle all {@link Salary} interactions.
 * </p>
 */
@RestController
public class SalaryController {
    private static final String SALARY_ID = "Salary id: ";
    private final InfSalaryRepository salaryRepository;

    private final InfTeacherRepository teacherRepository;
    private final InfGradeRepository gradeRepository;
    private final SalaryMapper salaryMapper;
    private final TeacherMapper teacherMapper;
    private final GradeMapper gradeMapper;

    public SalaryController(InfSalaryRepository salaryRepository,
                            SalaryMapper salaryMapper,
                            InfTeacherRepository teacherRepository,
                            TeacherMapper teacherMapper,
                            InfGradeRepository gradeRepository,
                            GradeMapper gradeMapper) {

        this.salaryRepository = salaryRepository;
        this.salaryMapper = salaryMapper;

        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;

        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;

    }

    /**
     * <p>Find all {@link Salary}s,convert them to {@link SalaryDto} and return them.</p>
     *
     * @return list of {@link SalaryDto}
     */
    @GetMapping("/salaries")
    public List<SalaryDto> retrieveAllSalaries() {
        return salaryRepository.findAll()
                .stream()
                .map(salaryMapper::toDto)
                .toList();
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
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isEmpty())
            throw new ItemNotFoundException(SALARY_ID + id);

        return salaryMapper.toDto(salary.get());
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
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isEmpty())
            throw new ItemNotFoundException(SALARY_ID + id);

        return teacherMapper.toDto(salary.get().getTeacher());
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
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isEmpty())
            throw new ItemNotFoundException(SALARY_ID + id);

        return gradeMapper.toDto(salary.get().getGrade());
    }

    /**
     * Find a specific {@link Salary} according to taken id and delete it.
     *
     * @param id of {@link Salary}
     */
    @DeleteMapping("/salaries/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);

        if (!salary.isEmpty()) {
            salaryRepository.deleteById(id);
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
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (teacher.isEmpty() || grade.isEmpty())
            throw new ItemNotFoundException("Student id: " + teacherId + " OR Grade id: " + gradeId);

        salary.setTeacher(teacher.get());
        salary.setGrade(grade.get());

        Salary savedSalary = salaryRepository.save(salary);

        EntityModel<SalaryDto> entityModel = EntityModel.of(salaryMapper.toDto(savedSalary));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveSalary(savedSalary.getSalaryId()));
        entityModel.add(link.withRel("playlist_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }
}
