package com.assignment.salary.service;

import com.assignment.salary.mapper.DataMapper;
import com.assignment.salary.model.SalarySurvey;
import com.assignment.salary.model.SalarySurveyDataLoad;
import com.assignment.salary.repository.SalaryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ImportDataService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private DataMapper csvMapper;

    public void loadSalarySurveysFromCsv(InputStream inputStream) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            CsvToBean<SalarySurveyDataLoad> csvToBean = new CsvToBeanBuilder<SalarySurveyDataLoad>(reader)
                    .withType(SalarySurveyDataLoad.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            saveDataToDB(csvToBean.parse());
        }
    }

    public void loadSalarySurveysFromJson(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        try {
            InputStreamReader reader = new InputStreamReader(inputStream);
            List<SalarySurveyDataLoad> dataLoads = objectMapper.readValue(reader, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, SalarySurveyDataLoad.class));
            saveDataToDB(dataLoads);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDataToDB(List<SalarySurveyDataLoad> dataLoads){
        List<SalarySurvey> salarySurveyList = new java.util.ArrayList<>(dataLoads
                .stream()
                .map(csvMapper::toSalarySurvey)
                .toList());

        //remove timestamp and salary = null
        salarySurveyList.removeIf(salarySurvey -> salarySurvey.getTimestamp() == null || salarySurvey.getTimestamp().isEmpty()
                || salarySurvey.getSalary()== null);
        salaryRepository.saveAll(salarySurveyList);
    }
}
