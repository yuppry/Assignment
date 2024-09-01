package com.assignment.salary.config;

import com.assignment.salary.service.ImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private ImportDataService importDataService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        if (fileConfig.getCsv().isActive()) {
            processCsvFile(fileConfig.getCsv().getPath());
        }

        if (fileConfig.getJson().isActive()) {
            processJsonFile(fileConfig.getJson().getPath());
        }
    }

    private void processCsvFile(String csvPath){
    ClassPathResource resource = new ClassPathResource(csvPath);
        try (InputStream inputStream = resource.getInputStream()) {
            importDataService.loadSalarySurveysFromCsv(inputStream);
            System.out.println("******* Load CSV data from " + csvPath + " *******");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processJsonFile(String jsonPath) throws IOException {
        ClassPathResource resource = new ClassPathResource(jsonPath);
        try (InputStream inputStream = resource.getInputStream()) {
            importDataService.loadSalarySurveysFromJson(inputStream);
            System.out.println("******* Load JSON data from " + jsonPath + " *******");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
