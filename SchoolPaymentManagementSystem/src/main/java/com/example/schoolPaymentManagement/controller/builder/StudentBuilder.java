package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.controller.StudentController;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfStudentRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link StudentController} constructor parameters.
 * </p>
 */
@Getter
@Component
public class StudentBuilder {
    private final InfStudentRepository studentRepository;
    private final InfGradeRepository gradeRepository;
    private final StudentMapper studentMapper;
    private final FeeMapper feeMapper;

    private StudentBuilder(Builder builder) {
        this.studentRepository = builder.studentRepository;
        this.gradeRepository = builder.gradeRepository;
        this.feeMapper = builder.feeMapper;
        this.studentMapper = builder.studentMapper;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfGradeRepository gradeRepository;

        @Autowired
        private InfStudentRepository studentRepository;

        @Autowired
        private StudentMapper studentMapper;

        @Autowired
        private FeeMapper feeMapper;
    }
}
