package ru.skillbox.janeskills.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google")
public class GoogleProperties {

    private String credFilePath;
    private String appName;
    private String tokens;
    private String spreadSheetsId;
    private String apiKey;

}
