package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.controller.builder.StudentBuilder;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfStudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImp implements StudentService {
    private static final String STUDENT_ID = "Student id: ";
    private final InfStudentRepository studentRepository;
    private final InfGradeRepository gradeRepository;
    private final StudentMapper studentMapper;
    private final FeeMapper feeMapper;
    private final GradeMapper gradeMapper;

    public StudentServiceImp(StudentBuilder studentBuilder) {
        this.studentRepository = studentBuilder.getStudentRepository();
        this.gradeRepository = studentBuilder.getGradeRepository();
        this.studentMapper = studentBuilder.getStudentMapper();
        this.feeMapper = studentBuilder.getFeeMapper();
        this.gradeMapper = studentBuilder.getGradeMapper();
    }

    @Override
    public List<StudentDto> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Override
    public StudentDto getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new ItemNotFoundException(STUDENT_ID + id);

        return studentMapper.toDto(student.get());
    }

    @Override
    public List<FeeDto> getFeesOfStudents(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new ItemNotFoundException(STUDENT_ID + id);

        return student.get()
                .getFees()
                .stream()
                .map(feeMapper::toDto)
                .toList();
    }

    @Override
    public List<GradeDto> getGradesOfStudents(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new ItemNotFoundException(STUDENT_ID + id);

        return student.get()
                .getGradeList()
                .stream()
                .map(gradeMapper::toDto)
                .toList();
    }

    @Override
    public Boolean deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isEmpty()) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public StudentDto createStudent(Student student, Long gradeId) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (grade.isEmpty())
            throw new ItemNotFoundException("Grade id : " + gradeId);

        List<Grade> grades = new ArrayList<>();
        grades.add(grade.get());

        student.setGradeList(grades);

        return studentMapper.toDto(studentRepository.save(student));
    }
}
