package com.assignment.salary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalarySurveyDataLoad {
    @JsonProperty("Timestamp")
    @CsvBindByName(column = "Timestamp")
    String timestamp;

    @JsonProperty("Employer")
    @CsvBindByName(column = "Employer")
    String employer;

    @JsonProperty("Location")
    @CsvBindByName(column = "Location")
    String location;

    @JsonProperty("Job Title")
    @CsvBindByName(column = "Job Title")
    String jobTitle;

    @JsonProperty("Years at Employer")
    @CsvBindByName(column = "Years at Employer")
    String yearsAtEmployer;

    @JsonProperty("Years of Experience")
    @CsvBindByName(column = "Years of Experience")
    String yearsOfExperience;

    @JsonProperty("Salary")
    @CsvBindByName(column = "Salary")
    String salary;

    @JsonProperty("Signing Bonus")
    @CsvBindByName(column = "Signing Bonus")
    String signingBonus;

    @JsonProperty("Annual Bonus")
    @CsvBindByName(column = "Annual Bonus")
    String annualBonus;

    @JsonProperty("Annual Stock Value/Bonus")
    @CsvBindByName(column = "Annual Stock Value/Bonus")
    String annualStockValue;

    @JsonProperty("Gender")
    @CsvBindByName(column = "Gender")
    String gender;

    @Column(length = 1000)
    @JsonProperty("Additional Comments")
    @CsvBindByName(column = "Additional Comments")
    String additionalComments;
}
