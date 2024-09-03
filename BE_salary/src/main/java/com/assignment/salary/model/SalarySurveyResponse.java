package com.assignment.salary.model;

import com.assignment.salary.serializer.BigDecimalCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SalarySurveyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timestamp;
    private String employer;
    private String location;
    private String jobTitle;
    private String yearsAtEmployer;
    private String yearsOfExperience;
    @JsonSerialize(using = BigDecimalCustomSerializer.class)
    private BigDecimal salary;
    private String signingBonus;
    private String annualBonus;
    private String annualStockValue;
    private String gender;
    @Column(length = 1000)
    private String additionalComments;
}
