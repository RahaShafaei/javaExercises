package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.controller.builder.TeacherBuilder;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.model.Teacher;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfTeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("teacherService")
public class TeacherServiceImp implements TeacherService {
    private static final String TEACHER_ID = "Teacher id: ";
    private final InfTeacherRepository teacherRepository;
    private final InfGradeRepository gradeRepository;
    private final TeacherMapper teacherMapper;
    private final SalaryMapper salaryMapper;
    private final GradeMapper gradeMapper;

    public TeacherServiceImp(TeacherBuilder teacherBuilder) {
        this.teacherRepository = teacherBuilder.getTeacherRepository();
        this.gradeRepository = teacherBuilder.getGradeRepository();
        this.teacherMapper = teacherBuilder.getTeacherMapper();
        this.salaryMapper = teacherBuilder.getSalaryMapper();
        this.gradeMapper = teacherBuilder.getGradeMapper();
    }

    @Override
    public List<TeacherDto> getTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .toList();
    }

    @Override
    public TeacherDto getTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new ItemNotFoundException(TEACHER_ID + id);

        return teacherMapper.toDto(teacher.get());
    }

    @Override
    public List<SalaryDto> getSalariesOfTeachers(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new ItemNotFoundException(TEACHER_ID + id);

        return teacher.get()
                .getSalaries()
                .stream()
                .map(salaryMapper::toDto)
                .toList();
    }

    @Override
    public List<GradeDto> getGradesOfTeachers(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new ItemNotFoundException(TEACHER_ID + id);

        return teacher.get()
                .getGradeList()
                .stream()
                .map(gradeMapper::toDto)
                .toList();
    }

    @Override
    public Boolean deleteTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (!teacher.isEmpty()) {
            teacherRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public TeacherDto createTeacher(Teacher teacher, Long gradeId) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (grade.isEmpty())
            throw new ItemNotFoundException("Grade id : " + gradeId);

        List<Grade> grades = new ArrayList<>();
        grades.add(grade.get());

        teacher.setGradeList(grades);

        return teacherMapper.toDto(teacherRepository.save(teacher));
    }
}
