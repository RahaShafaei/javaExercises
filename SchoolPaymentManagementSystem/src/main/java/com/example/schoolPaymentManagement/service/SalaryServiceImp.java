package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.controller.builder.SalaryBuilder;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.exception.ItemNotIncludedException;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Salary;
import com.example.schoolPaymentManagement.model.Teacher;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import com.example.schoolPaymentManagement.repository.InfTeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("salaryService")
public class SalaryServiceImp implements SalaryService {
    private static final String SALARY_ID = "Salary id: ";
    private final InfSalaryRepository salaryRepository;

    private final InfTeacherRepository teacherRepository;
    private final InfGradeRepository gradeRepository;
    private final SalaryMapper salaryMapper;
    private final TeacherMapper teacherMapper;
    private final GradeMapper gradeMapper;

    public SalaryServiceImp(SalaryBuilder salaryBuilder) {
        this.salaryRepository = salaryBuilder.getSalaryRepository();
        this.salaryMapper = salaryBuilder.getSalaryMapper();

        this.teacherRepository = salaryBuilder.getTeacherRepository();
        this.teacherMapper = salaryBuilder.getTeacherMapper();

        this.gradeRepository = salaryBuilder.getGradeRepository();
        this.gradeMapper = salaryBuilder.getGradeMapper();
    }

    @Override
    public List<SalaryDto> getSalaries() {
        return salaryRepository.findAll()
                .stream()
                .map(salaryMapper::toDto)
                .toList();
    }

    @Override
    public SalaryDto getSalary(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isEmpty())
            throw new ItemNotFoundException(SALARY_ID + id);

        return salaryMapper.toDto(salary.get());
    }

    @Override
    public TeacherDto getSalaryTeacher(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isEmpty())
            throw new ItemNotFoundException(SALARY_ID + id);

        return teacherMapper.toDto(salary.get().getTeacher());
    }

    @Override
    public GradeDto getSalaryGrade(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);

        if (salary.isEmpty())
            throw new ItemNotFoundException(SALARY_ID + id);

        return gradeMapper.toDto(salary.get().getGrade());
    }

    @Override
    public Boolean deleteSalary(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);

        if (!salary.isEmpty()) {
            salaryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SalaryDto createSalary(Salary salary, Long teacherId, Long gradeId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (teacher.isEmpty() || grade.isEmpty())
            throw new ItemNotFoundException("Student id: " + teacherId + " OR Grade id: " + gradeId);

        if (!grade.get().getTeacherList().contains(teacher.get()))
            throw new ItemNotIncludedException(
                    "There isn't any teacher by id: "
                            + teacherId
                            + " in grade by id: "
                            + gradeId
            );

        salary.setTeacher(teacher.get());
        salary.setGrade(grade.get());

        Salary savedSalary = salaryRepository.save(salary);

        return salaryMapper.toDto(savedSalary);
    }
}
