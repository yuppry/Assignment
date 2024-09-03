package com.assignment.salary.mapper;

import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.model.SalarySurveyDataLoad;
import com.assignment.salary.model.SalarySurveyResponse;
import com.assignment.salary.util.NumberUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Override
    public SalarySurveyResponse toSalarySurveyResponse(SalarySurvey salarySurvey) {
        if (salarySurvey == null) {
            return null;
        }
        BigDecimal salaryAsBigDecimal = salarySurvey.getSalaryAsBigDecimal();

        return new SalarySurveyResponse(salarySurvey.getId(), salarySurvey.getTimestamp(), salarySurvey.getEmployer(), salarySurvey.getLocation(),
                salarySurvey.getJobTitle(), salarySurvey.getYearsAtEmployer(), salarySurvey.getYearsOfExperience(), salaryAsBigDecimal,
                salarySurvey.getSigningBonus(), salarySurvey.getAnnualBonus(), salarySurvey.getAnnualStockValue(), salarySurvey.getGender(), salarySurvey.getAdditionalComments());
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
