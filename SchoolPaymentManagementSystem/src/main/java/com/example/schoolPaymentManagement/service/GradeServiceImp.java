package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.controller.builder.GradeBuilder;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("gradeService")
public class GradeServiceImp implements GradeService{

    private static final String GRADE_ID = "Grade id: ";
    private final InfGradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final FeeMapper feeMapper;
    private final SalaryMapper salaryMapper;

    public GradeServiceImp(GradeBuilder gradeBuilder) {
        this.gradeRepository = gradeBuilder.getGradeRepository();
        this.gradeMapper = gradeBuilder.getGradeMapper();
        this.studentMapper = gradeBuilder.getStudentMapper();
        this.teacherMapper = gradeBuilder.getTeacherMapper();
        this.feeMapper = gradeBuilder.getFeeMapper();
        this.salaryMapper = gradeBuilder.getSalaryMapper();
    }

    @Override
    public List<GradeDto> getGrades() {
        return gradeRepository.findAll()
                .stream()
                .map(gradeMapper::toDto)
                .toList();
    }

    @Override
    public GradeDto getGrade(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return gradeMapper.toDto(grade.get());
    }

    @Override
    public List<StudentDto> getGradeStudents(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getStudentList()
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Override
    public List<TeacherDto> getGradeTeachers(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getTeacherList()
                .stream()
                .map(teacherMapper::toDto)
                .toList();
    }

    @Override
    public List<FeeDto> getGradeFees(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getFees()
                .stream()
                .map(feeMapper::toDto)
                .toList();
    }

    @Override
    public List<SalaryDto> getSalaries(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);

        if (grade.isEmpty())
            throw new ItemNotFoundException(GRADE_ID + id);

        return grade.get()
                .getSalaries()
                .stream()
                .map(salaryMapper::toDto)
                .toList();
    }

    @Override
    public Boolean deleteGrade(Long id) {
        Optional<Grade> genre = gradeRepository.findById(id);

        if (!genre.isEmpty()) {
            gradeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GradeDto createGrade(Grade grade) {
        return gradeMapper.toDto(gradeRepository.save(grade)) ;
    }
}
