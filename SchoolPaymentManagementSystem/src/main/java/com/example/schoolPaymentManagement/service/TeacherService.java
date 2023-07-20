package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.model.Teacher;

import java.util.List;

public interface TeacherService {

    public List<TeacherDto> getTeachers();

    public TeacherDto getTeacher(Long id);

    public List<SalaryDto> getSalariesOfTeachers(Long id);

    public List<GradeDto> getGradesOfTeachers(Long id);

    public Boolean deleteTeacher(Long id);

    public TeacherDto createTeacher(Teacher teacher, Long gradeId);
}
