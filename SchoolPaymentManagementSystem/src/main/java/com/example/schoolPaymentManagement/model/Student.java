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
        property = "studentId")
@Entity
public class Student {
    @Id
    @GeneratedValue(generator = "student_sequence-generator")
    @GenericGenerator(
            name = "student_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "student_sequence"),
                    @Parameter(name = "initial_value", value = "10"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long studentId;

    @Size(min = 2, message = "{person.firstName.min}")
    private String firstName;
    @Size(min = 2, message = "{person.lastName.min}")
    private String lastName;

    @OneToMany(mappedBy = "student")
    private List<Fee> fees;

    @ManyToMany
    @JoinTable(
            name = "student_grade",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "gradeId"))
    private List<Grade> gradeList;

}
