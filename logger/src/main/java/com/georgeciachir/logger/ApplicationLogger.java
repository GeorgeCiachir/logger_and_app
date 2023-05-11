package com.georgeciachir.logger;

import com.georgeciachir.Logger;
import com.georgeciachir.config.Appenders;
import com.georgeciachir.LogLevel;
import com.georgeciachir.defaults.LoggingEvent;

public class ApplicationLogger implements Logger {

    private final String name;

    private LogLevel currentLevel;
    private String dateTimePattern;
    private Appenders appenders;

    ApplicationLogger(String name) {
        this.name = name;
    }

    @Override
    public void debug(String message) {
        if (!shouldLog(LogLevel.DEBUG)) {
            return;
        }

        logInternally(message, LogLevel.DEBUG);
    }

    @Override
    public void info(String message) {
        if (!shouldLog(LogLevel.INFO)) {
            return;
        }

        logInternally(message, LogLevel.INFO);
    }

    @Override
    public void warn(String message) {
        if (!shouldLog(LogLevel.WARNING)) {
            return;
        }

        logInternally(message, LogLevel.WARNING);
    }

    @Override
    public void error(String message) {
        if (!shouldLog(LogLevel.ERROR)) {
            return;
        }

        logInternally(message, LogLevel.ERROR);
    }

    @Override
    public String getName() {
        return name;
    }

    private boolean shouldLog(LogLevel requestedLevel) {
        return requestedLevel.getIntLevel() <= currentLevel.getIntLevel();
    }

    private void logInternally(String message, LogLevel level) {
        LoggingEvent loggingEvent = new LoggingEvent(this.name, level, message, this.dateTimePattern);
        appenders.appendLoggingEvent(loggingEvent);
    }

    public void setCurrentLevel(LogLevel currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setDateTimePattern(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
    }

    public void setAppenders(Appenders appenders) {
        this.appenders = appenders;
    }
}
