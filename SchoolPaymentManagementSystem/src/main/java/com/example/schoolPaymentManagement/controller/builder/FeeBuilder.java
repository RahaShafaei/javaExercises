package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.dto.FeeMapper;
import com.example.schoolPaymentManagement.dto.GradeMapper;
import com.example.schoolPaymentManagement.dto.StudentMapper;
import com.example.schoolPaymentManagement.model.Fee;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfStudentRepository;
import com.example.schoolPaymentManagement.controller.FeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link FeeController} constructor parameters.
 * </p>
 */
@Component
public class FeeBuilder {
    private final InfFeeRepository feeRepository;
    private final InfStudentRepository studentRepository;
    private final InfGradeRepository gradeRepository;
    private final FeeMapper feeMapper;
    private final StudentMapper studentMapper;
    private final GradeMapper gradeMapper;

    private FeeBuilder(Builder builder) {
        this.feeRepository = builder.feeRepository;
        this.studentRepository = builder.studentRepository;
        this.gradeRepository = builder.gradeRepository;
        this.feeMapper = builder.feeMapper;
        this.studentMapper = builder.studentMapper;
        this.gradeMapper = builder.gradeMapper;
    }

    public InfFeeRepository getFeeRepository() {
        return feeRepository;
    }

    public InfStudentRepository getStudentRepository() {
        return studentRepository;
    }

    public InfGradeRepository getGradeRepository() {
        return gradeRepository;
    }

    public FeeMapper getFeeMapper() {
        return feeMapper;
    }

    public StudentMapper getStudentMapper() {
        return studentMapper;
    }

    public GradeMapper getGradeMapper() {
        return gradeMapper;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfFeeRepository feeRepository;

        @Autowired
        private InfStudentRepository studentRepository;

        @Autowired
        private InfGradeRepository gradeRepository;

        @Autowired
        private FeeMapper feeMapper;

        @Autowired
        private StudentMapper studentMapper;

        @Autowired
        private GradeMapper gradeMapper;
    }
}
