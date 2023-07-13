package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.SalaryMapper;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.dto.TeacherMapper;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.model.Teacher;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfTeacherRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link Teacher} interactions.
 * </p>
 */
@RestController
public class TeacherController {
    private static final String TEACHER_ID = "Teacher id: ";
    private final InfTeacherRepository teacherRepository;
    private final InfGradeRepository gradeRepository;
    private final TeacherMapper teacherMapper;
    private final SalaryMapper salaryMapper;

    public TeacherController(InfTeacherRepository teacherRepository,
                             InfGradeRepository gradeRepository,
                             TeacherMapper teacherMapper,
                             SalaryMapper feeMapper) {
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
        this.teacherMapper = teacherMapper;
        this.salaryMapper = feeMapper;
    }

    /**
     * <p>Find all {@link Teacher}s,convert them to {@link TeacherDto} and return them.</p>
     *
     * @return list of {@link TeacherDto}
     */
    @GetMapping("/teachers")
    public List<TeacherDto> retrieveAllTeacher() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .toList();
    }

    /**
     * <p>
     * Find a specific {@link Teacher} according to taken id,
     * convert it to {@link TeacherDto} and return it.
     * </p>
     *
     * @param id of a valid {@link Teacher}.
     * @return A {@link TeacherDto}
     */
    @GetMapping("/teachers/{id}")
    public TeacherDto retrieveTeacher(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new ItemNotFoundException(TEACHER_ID + id);

        return teacherMapper.toDto(teacher.get());
    }

    /**
     * <p>
     * Find a specific {@link Teacher} according to taken id,
     * get all of its {@link Salary}, convert them to {@link SalaryDto} and return them.
     * </p>
     *
     * @return list of {@link SalaryDto}
     */
    @GetMapping("/teachers/{id}/salaries")
    public List<SalaryDto> retrieveSalariesOfTeachers(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new ItemNotFoundException(TEACHER_ID + id);

        return teacher.get()
                .getSalaries()
                .stream()
                .map(salaryMapper::toDto)
                .toList();
    }

    /**
     * Find a specific {@link Teacher} according to taken id and delete it.
     *
     * @param id of a valid {@link Teacher}.
     */
    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new ItemNotFoundException(TEACHER_ID + id);

        teacherRepository.deleteById(id);
    }

    /**
     * <p>Create a {@link Teacher} of a valid {@link Grade} </p>
     *
     * @param teacher object of {@link Teacher}.
     * @param gradeId id of a valid {@link Grade}
     * @return {@link ResponseEntity} of {@link EntityModel} of {@link TeacherDto}
     */
    @PostMapping("/teachers/grade/{gradeId}")
    public ResponseEntity<EntityModel<TeacherDto>> createTeacher(@Valid @RequestBody Teacher teacher,
                                                                 @PathVariable Long gradeId) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (grade.isEmpty())
            throw new ItemNotFoundException("Grade id : " + gradeId);

        List<Grade> grades = new ArrayList<>();
        grades.add(grade.get());

        teacher.setGradeList(grades);
        Teacher teacherSaved = teacherRepository.save(teacher);

        EntityModel<TeacherDto> entityModel = EntityModel.of(teacherMapper.toDto(teacherSaved));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveTeacher(teacherSaved.getTeacherId()));
        entityModel.add(link.withRel("student_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}
