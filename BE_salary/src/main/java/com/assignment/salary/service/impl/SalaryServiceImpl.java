package com.assignment.salary.service.impl;

import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.repository.SalaryRepository;
import com.assignment.salary.service.SalaryService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;


    @Override
    public List<SalarySurvey> getAllSurveys() {
        return salaryRepository.findAll();
    }

    @Override
    public List<SalarySurvey> findSalarySurveyByGenderAndJobTitle(String gender, String jobTitle,Long salaryGte, Long salaryLte,
                                                                  Long salaryGt, Long salaryLt, Long salaryEq) {
        return salaryRepository.findSalarySurveyByGenderAndJobTitleAndSalary(gender,jobTitle,salaryGte,salaryLte,salaryGt,salaryLt,salaryEq);
    }

    @Override
    public List<SalarySurvey> findSalarySurveySortedByField(String[] sortBy, String sortType) {
        Sort.Direction direction = sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,sortBy);
        return salaryRepository.findAll(sort);
    }

    @Override
    public List<Map<String, Object>> filterData(List<String> fields) {
        if (fields.isEmpty()){
            throw new IllegalArgumentException("Fields must be provided.");
        }
        List<SalarySurvey> getAllData = salaryRepository.findAll();
        boolean allColumnsExist = checkColumnsExist(fields);

        if (!allColumnsExist) {
            throw new IllegalArgumentException("One or more fields do not exist.");
        }

        return getAllData.stream()
                .map(salarySurvey -> filterSalarySurvey(salarySurvey, fields))
                .collect(Collectors.toList());
    }

    private boolean checkColumnsExist(List<String> columns) {
        Set<String> validColumns = Arrays.stream(SalarySurvey.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toSet());

        return validColumns.containsAll(columns);
    }

    private Map<String, Object> filterSalarySurvey(SalarySurvey survey, List<String> fields) {
        Map<String, Object> filteredData = new HashMap<>();

        if (fields.contains("id")) {
            filteredData.put("id", survey.getId());
        }
        if (fields.contains("timestamp")) {
            filteredData.put("timestamp", survey.getTimestamp());
        }
        if (fields.contains("employer")) {
            filteredData.put("employer", survey.getEmployer());
        }
        if (fields.contains("location")) {
            filteredData.put("location", survey.getLocation());
        }
        if (fields.contains("jobTitle")) {
            filteredData.put("jobTitle", survey.getJobTitle());
        }
        if (fields.contains("yearsAtEmployer")) {
            filteredData.put("yearsAtEmployer", survey.getYearsAtEmployer());
        }
        if (fields.contains("yearsOfExperience")) {
            filteredData.put("yearsOfExperience", survey.getYearsOfExperience());
        }
        if (fields.contains("salary")) {
            filteredData.put("salary", survey.getSalary());
        }
        if (fields.contains("signingBonus")) {
            filteredData.put("signingBonus", survey.getSigningBonus());
        }
        if (fields.contains("annualBonus")) {
            filteredData.put("annualBonus", survey.getAnnualBonus());
        }
        if (fields.contains("annualStockValue")) {
            filteredData.put("annualStockValue", survey.getAnnualStockValue());
        }
        if (fields.contains("gender")) {
            filteredData.put("gender", survey.getGender());
        }
        if (fields.contains("additionalComments")) {
            filteredData.put("additionalComments", survey.getAdditionalComments());
        }
        return filteredData;
    }
}
