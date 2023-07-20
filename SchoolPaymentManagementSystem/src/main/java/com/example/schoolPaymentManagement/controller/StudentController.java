package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.service.StudentService;
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
 * Handle all {@link Student} interactions.
 * </p>
 */
@AllArgsConstructor
@RestController
public class StudentController {
    private final StudentService studentService;

    /**
     * <p>Find all {@link Student}s,convert them to {@link StudentDto} and return them.</p>
     *
     * @return list of {@link StudentDto}
     */
    @GetMapping("/students")
    public List<StudentDto> retrieveAllStudent() {
        return studentService.getStudents();
    }

    /**
     * <p>
     * Find a specific {@link Student} according to taken id,
     * convert it to {@link StudentDto} and return it.
     * </p>
     *
     * @param id of a valid {@link Student}.
     * @return A {@link StudentDto}
     */
    @GetMapping("/students/{id}")
    public StudentDto retrieveStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    /**
     * <p>
     * Find a specific {@link Student} according to taken id,
     * get all of its {@link Fee}, convert them to {@link FeeDto} and return them.
     * </p>
     *
     * @param id of a valid {@link Student}.
     * @return list of {@link FeeDto}
     */
    @GetMapping("/students/{id}/fees")
    public List<FeeDto> retrieveFeesOfStudents(@PathVariable Long id) {
        return studentService.getFeesOfStudents(id);
    }

    /**
     * <p>
     * Find a specific {@link Student} according to taken id,
     * get all of its {@link Grade}, convert them to {@link GradeDto} and return them.
     * </p>
     *
     * @param id of a valid {@link Student}.
     * @return list of {@link GradeDto}
     */
    @GetMapping("/students/{id}/grades")
    public List<GradeDto> retrieveGradesOfStudents(@PathVariable Long id) {
        return studentService.getGradesOfStudents(id);
    }

    /**
     * Find a specific {@link Student} according to taken id and delete it.
     *
     * @param id of a valid {@link Student}.
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean result = studentService.deleteStudent(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * <p>Create a {@link Student} of a valid {@link Grade} </p>
     *
     * @param student object of {@link Student}.
     * @param gradeId id of a valid {@link Grade}
     * @return {@link ResponseEntity} of {@link EntityModel} of {@link StudentDto}
     */
    @PostMapping("/students/grade/{gradeId}")
    public ResponseEntity<EntityModel<StudentDto>> createStudent(@Valid @RequestBody Student student,
                                                                 @PathVariable Long gradeId) {
        StudentDto savedStudent = studentService.createStudent(student, gradeId);

        EntityModel<StudentDto> entityModel = EntityModel.of(savedStudent);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveStudent(savedStudent.getStudentId()));
        entityModel.add(link.withRel("student_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}
