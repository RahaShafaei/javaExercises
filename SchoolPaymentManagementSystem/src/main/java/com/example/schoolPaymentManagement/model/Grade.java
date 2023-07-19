package com.example.schoolPaymentManagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
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

}
