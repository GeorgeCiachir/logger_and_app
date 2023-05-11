package com.georgeciachir.config;

import com.georgeciachir.LogLevel;

public class RootConfig {

    private LogLevel level;
    private String dateTimePattern;

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getDateTimePattern() {
        return dateTimePattern;
    }

    public void setDateTimePattern(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
    }
}
