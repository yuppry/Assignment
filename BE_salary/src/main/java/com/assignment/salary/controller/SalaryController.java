package com.assignment.salary.controller;

import com.assignment.salary.exception.ErrorResponse;
import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.model.SalarySurveyResponse;
import com.assignment.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job_data")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllSurveys() {
        List<SalarySurveyResponse> data =salaryService.getAllSurveys();
        return buildResponse(data);
    }

    @GetMapping
    public ResponseEntity<?> getSurveysByGenderOrJobTitleOrSalary(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(value = "salary_gte",required = false) Long salaryGte,
            @RequestParam(value = "salary_lte",required = false) Long salaryLte,
            @RequestParam(value = "salary_gt",required = false) Long salaryGt,
            @RequestParam(value = "salary_lt",required = false) Long salaryLt,
            @RequestParam(value = "salary_eq",required = false) Long salaryEq){
        List<SalarySurveyResponse> data = salaryService.findSalarySurveyByGenderAndJobTitle(gender,jobTitle,salaryGte,salaryLte,salaryGt,salaryLt,salaryEq);

        return buildResponse(data);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getSurveysByFields(@RequestParam List<String> fields) {
        List<Map<String, Object>> data = salaryService.filterData(fields);

        return buildResponse(data);
    }

    @GetMapping("/sort")
    public ResponseEntity<?> getSurveySortedByField(
            @RequestParam(required = false) String[] sortBy,
            @RequestParam(value = "sort_type",required = false) String sortType) {
        List<SalarySurveyResponse> data = salaryService.findSalarySurveySortedByField(sortBy,sortType);

        return buildResponse(data);
    }

    private ResponseEntity<?> buildResponse(Object data) {
    //to check data is empty or not
        if (data instanceof List<?> && ((List<?>) data).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "No data found for the given fields/attributes."));
        }
        return ResponseEntity.ok(data);
    }

}
