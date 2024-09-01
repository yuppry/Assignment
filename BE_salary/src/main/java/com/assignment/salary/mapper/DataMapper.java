package com.assignment.salary.mapper;

import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.model.SalarySurveyDataLoad;


public interface DataMapper {

    SalarySurvey toSalarySurvey (SalarySurveyDataLoad salarySurveyDataLoad);
}
