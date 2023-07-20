package com.example.schoolPaymentManagement.service;

import com.example.schoolPaymentManagement.controller.builder.FeeBuilder;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.exception.ItemNotFoundException;
import com.example.schoolPaymentManagement.exception.ItemNotIncludedException;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.model.Grade;
import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("feeService")
public class FeeServiceImp implements FeeService {
    private static final String FEE_ID = "Fee id: ";
    private final InfFeeRepository feeRepository;
    private final InfStudentRepository studentRepository;
    private final InfGradeRepository gradeRepository;
    private final FeeMapper feeMapper;
    private final StudentMapper studentMapper;
    private final GradeMapper gradeMapper;

    public FeeServiceImp(FeeBuilder feeBuilder) {
        this.feeRepository = feeBuilder.getFeeRepository();
        this.feeMapper = feeBuilder.getFeeMapper();

        this.studentRepository = feeBuilder.getStudentRepository();
        this.studentMapper = feeBuilder.getStudentMapper();

        this.gradeRepository = feeBuilder.getGradeRepository();
        this.gradeMapper = feeBuilder.getGradeMapper();
    }

    @Override
    public List<FeeDto> getFees() {
        return feeRepository.findAll()
                .stream()
                .map(feeMapper::toDto)
                .toList();
    }

    @Override
    public FeeDto getFee(Long id) {
        Optional<Fee> fee = feeRepository.findById(id);

        if (fee.isEmpty())
            throw new ItemNotFoundException(FEE_ID + id);

        return feeMapper.toDto(fee.get());
    }

    @Override
    public StudentDto getFeeStudent(Long id) {
        Optional<Fee> fee = feeRepository.findById(id);

        if (fee.isEmpty())
            throw new ItemNotFoundException(FEE_ID + id);

        return studentMapper.toDto(fee.get().getStudent());
    }

    @Override
    public GradeDto getFeeGrade(Long id) {
        Optional<Fee> fee = feeRepository.findById(id);

        if (fee.isEmpty())
            throw new ItemNotFoundException(FEE_ID + id);

        return gradeMapper.toDto(fee.get().getGrade());
    }

    @Override
    public Boolean deleteFee(Long id) {
        Optional<Fee> fee = feeRepository.findById(id);

        if (!fee.isEmpty()) {
            feeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FeeDto createFee(Fee fee, Long studentId, Long gradeId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (student.isEmpty() || grade.isEmpty())
            throw new ItemNotFoundException(
                    "Student id: "
                            + studentId
                            + " OR Grade id: "
                            + gradeId
            );

        if (!grade.get().getStudentList().contains(student.get()))
            throw new ItemNotIncludedException(
                    "There isn't any student by id: "
                            + studentId
                            + " in grade by id: "
                            + gradeId
            );

        fee.setStudent(student.get());
        fee.setGrade(grade.get());

        Fee savedFee = feeRepository.save(fee);

        return feeMapper.toDto(savedFee);
    }
}
