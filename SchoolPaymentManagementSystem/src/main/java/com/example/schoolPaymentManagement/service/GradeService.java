package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.model.Grade;

import java.util.List;

public interface GradeService {
    public List<GradeDto> getGrades();

    public GradeDto getGrade(Long id);

    public List<StudentDto> getGradeStudents(Long id);

    public List<TeacherDto> getGradeTeachers(Long id);

    public List<FeeDto> getGradeFees(Long id);

    public List<SalaryDto> getSalaries(Long id);

    public Boolean deleteGrade(Long id);

    public GradeDto createGrade(Grade grade);
}
