package com.georgeciachir.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgeciachir.Logger;

import java.io.IOException;
import java.io.InputStream;

public class ConfigurationManager {

    private static final String CONFIG_FILE_NAME = "logger-configuration.json";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        readConfiguration();
    }

    private static LoggerConfigurer loggerConfigurer;

    public static void updateConfiguration(LoggerConfiguration configuration) {
        validateConfiguration(configuration);
        loggerConfigurer.updateLoggers(configuration);
    }

    public static void configure(Logger logger) {
        loggerConfigurer.configure(logger);
    }

    private static void readConfiguration() {
        try (InputStream is = LoggerConfigurer.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            LoggerConfiguration loggerConfiguration = OBJECT_MAPPER.readValue(is, LoggerConfiguration.class);
            validateConfiguration(loggerConfiguration);
            loggerConfigurer = new LoggerConfigurer(loggerConfiguration);
        } catch (IOException e) {
            throw new IllegalStateException("Logging can't be configured", e);
        }
    }

    private static void validateConfiguration(LoggerConfiguration configuration) {
        // do validation
    }
}
