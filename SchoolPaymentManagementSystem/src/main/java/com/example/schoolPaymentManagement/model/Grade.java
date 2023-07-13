package com.example.schoolPaymentManagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "gradeId")
@Entity
public class Grade {
    @Id
    @GeneratedValue(generator = "grade_sequence-generator")
    @GenericGenerator(
            name = "grade_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "grade_sequence"),
                    @Parameter(name = "initial_value", value = "6"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long gradeId;

    @Size(min = 2, message = "Name should have at least 2 character")
    private String name;

    @OneToMany(mappedBy = "grade")
    private List<Fee> fees;

    @OneToMany(mappedBy = "grade")
    private List<Salary> salaries;

    @ManyToMany(mappedBy = "gradeList")
    private List<Student> studentList;

    @ManyToMany(mappedBy = "gradeList")
    private List<Teacher> teacherList;

    public Grade() {
    }

    public long getGradeId() {
        return gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", name='" + name + '\'' +
                ", fees=" + fees +
                ", salaries=" + salaries +
                ", studentList=" + studentList +
                ", teacherList=" + teacherList +
                '}';
    }
}
