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
        property = "teacherId")
@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator = "teacher_sequence-generator")
    @GenericGenerator(
            name = "teacher_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "teacher_sequence"),
                    @Parameter(name = "initial_value", value = "10"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long teacherId;

    @Size(min = 2, message = "FirstName should have at least 2 character")
    private String firstName;
    @Size(min = 2, message = "LastName should have at least 2 character")
    private String lastName;

    @OneToMany(mappedBy = "teacher")
    private List<Salary> salaries;

    @ManyToMany
    @JoinTable(
            name = "teacher_grade",
            joinColumns = @JoinColumn(name = "teacherId"),
            inverseJoinColumns = @JoinColumn(name = "gradeId"))
    private List<Grade> gradeList;



}
