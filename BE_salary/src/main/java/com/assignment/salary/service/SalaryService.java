package com.assignment.salary.service;

import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.model.SalarySurveyResponse;

import java.util.List;
import java.util.Map;

public interface SalaryService {
    List<SalarySurveyResponse> getAllSurveys();
    List<SalarySurveyResponse> findSalarySurveyByGenderAndJobTitle(String gender, String jobTitle, Long salaryGte, Long salaryLte,
                                                                   Long salaryGt, Long salaryLt, Long salaryEq);

    List<SalarySurveyResponse> findSalarySurveySortedByField(String[] sortBy, String sortType);

    List<Map<String, Object>> filterData(List<String> fields);
}
