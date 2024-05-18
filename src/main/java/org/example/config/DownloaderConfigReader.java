package org.example.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.file.ConfigFileReader;

public class DownloaderConfigReader {

    private final String configFileName;

    public DownloaderConfigReader(String configFileName) {
        this.configFileName = configFileName;
    }

    public DownloaderConfigData readConfig() {
        ConfigFileReader fileReader = new ConfigFileReader();
        String fileContent = fileReader.readFile(configFileName);
        if (fileContent == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(fileContent, DownloaderConfigData.class);
        } catch (JsonProcessingException e) {
            System.out.println("could not parse config" + e.getMessage());
        }
        return null;
    }
}
