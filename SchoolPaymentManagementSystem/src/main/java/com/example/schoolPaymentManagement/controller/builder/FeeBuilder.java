package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.dto.FeeMapper;
import com.example.schoolPaymentManagement.dto.GradeMapper;
import com.example.schoolPaymentManagement.dto.StudentMapper;
import com.example.schoolPaymentManagement.repository.InfFeeRepository;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfStudentRepository;
import com.example.schoolPaymentManagement.controller.FeeController;
import lombok.Getter;
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
@Getter
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
