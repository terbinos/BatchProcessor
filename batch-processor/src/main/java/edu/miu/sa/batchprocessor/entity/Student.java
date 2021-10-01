package edu.miu.sa.batchprocessor.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FirstName;
    private String LastName;
    private double gpa;
    private LocalDateTime dob;

    @Transient
    private int age;
}
