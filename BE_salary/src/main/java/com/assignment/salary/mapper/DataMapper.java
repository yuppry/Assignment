package com.assignment.salary.mapper;

import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.model.SalarySurveyDataLoad;
import com.assignment.salary.model.SalarySurveyResponse;


public interface DataMapper {

    SalarySurvey toSalarySurvey (SalarySurveyDataLoad salarySurveyDataLoad);

    SalarySurveyResponse toSalarySurveyResponse (SalarySurvey salarySurvey);
}
