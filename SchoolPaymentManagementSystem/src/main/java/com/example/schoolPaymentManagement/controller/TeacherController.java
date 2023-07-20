package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.model.Teacher;
import com.example.schoolPaymentManagement.service.TeacherService;
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
 * Handle all {@link Teacher} interactions.
 * </p>
 */
@AllArgsConstructor
@RestController
public class TeacherController {
    TeacherService teacherService;

    /**
     * <p>Find all {@link Teacher}s,convert them to {@link TeacherDto} and return them.</p>
     *
     * @return list of {@link TeacherDto}
     */
    @GetMapping("/teachers")
    public List<TeacherDto> retrieveAllTeacher() {
        return teacherService.getTeachers();
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
        return teacherService.getTeacher(id);
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
        return teacherService.getSalariesOfTeachers(id);
    }

    /**
     * <p>
     * Find a specific {@link Teacher} according to taken id,
     * get all of its {@link Grade}, convert them to {@link GradeDto} and return them.
     * </p>
     *
     * @param id of a valid {@link Teacher}.
     * @return list of {@link GradeDto}
     */
    @GetMapping("/teachers/{id}/grades")
    public List<GradeDto> retrieveGradesOTeachers(@PathVariable Long id) {
        return teacherService.getGradesOfTeachers(id);
    }

    /**
     * Find a specific {@link Teacher} according to taken id and delete it.
     *
     * @param id of a valid {@link Teacher}.
     */
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        boolean result = teacherService.deleteTeacher(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
        TeacherDto teacherSaved = teacherService.createTeacher(teacher, gradeId);

        EntityModel<TeacherDto> entityModel = EntityModel.of(teacherSaved);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveTeacher(teacherSaved.getTeacherId()));
        entityModel.add(link.withRel("teacher_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}
