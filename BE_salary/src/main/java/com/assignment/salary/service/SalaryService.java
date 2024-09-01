package com.assignment.salary.service;

import com.assignment.salary.model.SalarySurvey;

import java.util.List;
import java.util.Map;

public interface SalaryService {
    List<SalarySurvey> getAllSurveys();
    List<SalarySurvey> findSalarySurveyByGenderAndJobTitle(String gender, String jobTitle, Long salaryGte, Long salaryLte,
                                                           Long salaryGt, Long salaryLt, Long salaryEq);

    List<SalarySurvey> findSalarySurveySortedByField(String[] sortBy, String sortType);

    List<Map<String, Object>> filterData(List<String> fields);
}
