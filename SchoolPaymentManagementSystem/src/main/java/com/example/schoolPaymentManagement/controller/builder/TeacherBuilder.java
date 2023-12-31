package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.controller.TeacherController;
import com.example.schoolPaymentManagement.dto.GradeMapper;
import com.example.schoolPaymentManagement.dto.SalaryMapper;
import com.example.schoolPaymentManagement.dto.TeacherMapper;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfTeacherRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link TeacherController} constructor parameters.
 * </p>
 */
@Getter
@Component
public class TeacherBuilder {
    private final InfTeacherRepository teacherRepository;
    private final InfGradeRepository gradeRepository;
    private final TeacherMapper teacherMapper;
    private final SalaryMapper salaryMapper;
    private final GradeMapper gradeMapper;

    private TeacherBuilder(Builder builder) {
        this.teacherMapper = builder.teacherMapper;
        this.salaryMapper = builder.salaryMapper;
        this.gradeMapper = builder.gradeMapper;
        this.gradeRepository = builder.gradeRepository;
        this.teacherRepository = builder.teacherRepository;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfGradeRepository gradeRepository;

        @Autowired
        private TeacherMapper teacherMapper;

        @Autowired
        private InfTeacherRepository teacherRepository;

        @Autowired
        private SalaryMapper salaryMapper;

        @Autowired
        private GradeMapper gradeMapper;
    }

}
