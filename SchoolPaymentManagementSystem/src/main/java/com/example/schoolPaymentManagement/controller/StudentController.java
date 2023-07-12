package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.FeeMapper;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.dto.StudentMapper;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfStudentRepository;
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
 * Handle all {@link Student} interactions.
 * </p>
 */
@RestController
public class StudentController {
    private static final String STUDENT_ID = "Student id: ";
    private final InfStudentRepository studentRepository;
    private final InfGradeRepository gradeRepository;
    private final StudentMapper studentMapper;
    private final FeeMapper feeMapper;

    public StudentController(InfStudentRepository studentRepository,
                             InfGradeRepository gradeRepository,
                             StudentMapper studentMapper,
                             FeeMapper feeMapper) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.studentMapper = studentMapper;
        this.feeMapper = feeMapper;
    }

    /**
     * <p>Find all {@link Student}s,convert them to {@link StudentDto} and return them.</p>
     *
     * @return list of {@link StudentDto}
     */
    @GetMapping("/students")
    public List<StudentDto> retrieveAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
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
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new ItemNotFoundException(STUDENT_ID + id);

        return studentMapper.toDto(student.get());
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
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new ItemNotFoundException(STUDENT_ID + id);

        return student.get()
                .getFees()
                .stream()
                .map(feeMapper::toDto)
                .toList();
    }

    /**
     * Find a specific {@link Student} according to taken id and delete it.
     *
     * @param id of a valid {@link Student}.
     */
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new ItemNotFoundException(STUDENT_ID + id);

        studentRepository.deleteById(id);
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

        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (grade.isEmpty())
            throw new ItemNotFoundException("Grade id : " + gradeId);

        List<Grade> grades = new ArrayList<>();
        grades.add(grade.get());

        student.setGradeList(grades);
        Student savedStudent = studentRepository.save(student);

        EntityModel<StudentDto> entityModel = EntityModel.of(studentMapper.toDto(savedStudent));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveStudent(savedStudent.getStudentId()));
        entityModel.add(link.withRel("student_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}
