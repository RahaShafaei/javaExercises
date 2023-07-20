package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.GradeDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.model.Fee;

import java.util.List;

public interface FeeService {
    public List<FeeDto> getFees();

    public FeeDto getFee(Long id);

    public StudentDto getFeeStudent(Long id);

    public GradeDto getFeeGrade(Long id);

    public Boolean deleteFee(Long id);

    public FeeDto createFee(Fee fee, Long studentId, Long gradeId);
}
