package com.assignment.salary.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class SalarySurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timestamp;
    private String employer;
    private String location;
    private String jobTitle;
    private String yearsAtEmployer;
    private String yearsOfExperience;
    private Long salary;
    private String signingBonus;
    private String annualBonus;
    private String annualStockValue;
    private String gender;
    @Column(length = 1000)
    private String additionalComments;
}
