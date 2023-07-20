package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.model.Salary;

import java.util.List;

public interface SalaryService {
    public List<SalaryDto> getSalaries();

    public SalaryDto getSalary(Long id);

    public TeacherDto getSalaryTeacher(Long id);

    public GradeDto getSalaryGrade(Long id);

    public Boolean deleteSalary(Long id);

    public SalaryDto createSalary(Salary salary, Long teacherId, Long gradeId);
}
