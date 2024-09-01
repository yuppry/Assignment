package com.assignment.salary.mapper;

import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.model.SalarySurveyDataLoad;
import com.assignment.salary.util.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class DataMapperImpl implements DataMapper {
    @Override
    public SalarySurvey toSalarySurvey(SalarySurveyDataLoad salarySurveyDataLoad) {
        if ( salarySurveyDataLoad == null ) {
            return null;
        }

        SalarySurvey salarySurvey = new SalarySurvey();

        salarySurvey.setTimestamp(salarySurveyDataLoad.getTimestamp());
        salarySurvey.setEmployer(salarySurveyDataLoad.getEmployer());
        salarySurvey.setLocation(salarySurveyDataLoad.getLocation());
        salarySurvey.setJobTitle(salarySurveyDataLoad.getJobTitle());
        salarySurvey.setYearsAtEmployer(salarySurveyDataLoad.getYearsAtEmployer());
        salarySurvey.setYearsOfExperience(salarySurveyDataLoad.getYearsOfExperience());
        salarySurvey.setSalary(validatedNumber(salarySurveyDataLoad.getSalary()));
        salarySurvey.setSigningBonus(salarySurveyDataLoad.getSigningBonus());
        salarySurvey.setAnnualBonus(salarySurveyDataLoad.getAnnualBonus());
        salarySurvey.setAnnualStockValue(salarySurveyDataLoad.getAnnualStockValue());
        salarySurvey.setGender(salarySurveyDataLoad.getGender());
        salarySurvey.setAdditionalComments(salarySurveyDataLoad.getAdditionalComments());

        return salarySurvey;
    }

    private Long validatedNumber(String input){
        Long temp = null;
        String stringWithOutComma = NumberUtils.regexReplaceString(input);
        if (!NumberUtils.containsAnyNonNumericChars(stringWithOutComma)){
             temp = Long.parseLong(stringWithOutComma);
        }
        return temp;
    }
}
