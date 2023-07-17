package com.example.schoolPaymentManagement.controller.builder;

import com.example.schoolPaymentManagement.controller.SalaryController;
import com.example.schoolPaymentManagement.dto.*;
import com.example.schoolPaymentManagement.repository.InfGradeRepository;
import com.example.schoolPaymentManagement.repository.InfSalaryRepository;
import com.example.schoolPaymentManagement.repository.InfTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link SalaryController} constructor parameters.
 * </p>
 */
@Component
public class SalaryBuilder {

    private final InfSalaryRepository salaryRepository;

    private final InfTeacherRepository teacherRepository;
    private final InfGradeRepository gradeRepository;
    private final SalaryMapper salaryMapper;
    private final TeacherMapper teacherMapper;
    private final GradeMapper gradeMapper;

    private SalaryBuilder(Builder builder) {
        this.teacherMapper = builder.teacherMapper;
        this.salaryMapper = builder.salaryMapper;
        this.gradeRepository = builder.gradeRepository;
        this.salaryRepository = builder.salaryRepository;
        this.teacherRepository = builder.teacherRepository;
        this.gradeMapper = builder.gradeMapper;
    }

    public InfSalaryRepository getSalaryRepository() {
        return salaryRepository;
    }

    public InfTeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public InfGradeRepository getGradeRepository() {
        return gradeRepository;
    }

    public SalaryMapper getSalaryMapper() {
        return salaryMapper;
    }

    public TeacherMapper getTeacherMapper() {
        return teacherMapper;
    }

    public GradeMapper getGradeMapper() {
        return gradeMapper;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfGradeRepository gradeRepository;

        @Autowired
        private GradeMapper gradeMapper;

        @Autowired
        private InfSalaryRepository salaryRepository;

        @Autowired
        private TeacherMapper teacherMapper;

        @Autowired
        private InfTeacherRepository teacherRepository;

        @Autowired
        private SalaryMapper salaryMapper;
    }
}
