package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.model.Student;

import java.util.List;

public interface StudentService {
    public List<StudentDto> getStudents();

    public StudentDto getStudent(Long id);

    public List<FeeDto> getFeesOfStudents(Long id);

    public List<GradeDto> getGradesOfStudents(Long id);

    public Boolean deleteStudent(Long id);

    public StudentDto createStudent(Student student, Long gradeId);
}
