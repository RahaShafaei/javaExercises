package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.controller.GradeController;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link GradeController} constructor parameters.
 * </p>
 */
@Getter
@Component
public class GradeBuilder {
    private final InfGradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final FeeMapper feeMapper;
    private final SalaryMapper salaryMapper;

    private GradeBuilder(Builder builder) {
        this.teacherMapper = builder.teacherMapper;
        this.salaryMapper = builder.salaryMapper;
        this.gradeRepository = builder.gradeRepository;
        this.feeMapper = builder.feeMapper;
        this.studentMapper = builder.studentMapper;
        this.gradeMapper = builder.gradeMapper;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfGradeRepository gradeRepository;

        @Autowired
        private GradeMapper gradeMapper;

        @Autowired
        private StudentMapper studentMapper;

        @Autowired
        private TeacherMapper teacherMapper;

        @Autowired
        private FeeMapper feeMapper;

        @Autowired
        private SalaryMapper salaryMapper;
    }
}
