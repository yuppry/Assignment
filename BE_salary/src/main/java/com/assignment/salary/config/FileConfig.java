package com.assignment.salary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "files")
public class FileConfig {
    private CsvConfig csv;
    private JsonConfig json;

    public CsvConfig getCsv() { return csv; }
    public void setCsv(CsvConfig csv) { this.csv = csv; }

    public JsonConfig getJson() { return json; }
    public void setJson(JsonConfig json) { this.json = json; }

    public static class CsvConfig {
        private String path;
        private boolean active;

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
    }

    public static class JsonConfig {
        private String path;
        private boolean active;

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
    }
}
