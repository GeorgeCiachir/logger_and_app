package com.georgeciachir.config;

import com.georgeciachir.Logger;
import com.georgeciachir.logger.LoggerRegistry;

public class LoggerConfigurer {

    private final AppenderFactory appenderFactory = new AppenderFactory();
    private volatile Appenders appenders;
    private LoggerConfiguration loggerConfiguration;

    public LoggerConfigurer(LoggerConfiguration loggerConfiguration) {
        this.loggerConfiguration = loggerConfiguration;
    }

    void configure(Logger logger) {
        configureInternally(logger, getAppenders());
    }

    void updateLoggers(LoggerConfiguration configuration) {
        loggerConfiguration = configuration;
        this.appenders = recreateAppenders();
        LoggerRegistry.getLoggers().forEach(logger -> configureInternally(logger, appenders));
    }

    private void configureInternally(Logger logger, Appenders appenders) {
        logger.setCurrentLevel(loggerConfiguration.getRoot().getLevel());
        logger.setDateTimePattern(loggerConfiguration.getRoot().getDateTimePattern());
        logger.setAppenders(appenders);
    }

    private Appenders recreateAppenders() {
        return appenderFactory.create(loggerConfiguration);
    }

    // do it only once at startup
    private Appenders getAppenders() {
        if (appenders == null) {
            synchronized (this) {
                if (appenders == null) {
                    appenders = recreateAppenders();
                }
            }
        }
        return appenders;
    }
}
