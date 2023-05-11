package com.georgeciachir.defaults;

import com.georgeciachir.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingEvent {

    private static final String DELIMITER = "\t";

    private final String loggerName;
    private final LogLevel level;
    private final String message;
    private final DateTimeFormatter formatter;

    public LoggingEvent(String loggerName, LogLevel level, String message, String dateTimePattern) {
        this.loggerName = loggerName;
        this.level = level;
        this.message = message;
        this.formatter = DateTimeFormatter.ofPattern(dateTimePattern);
    }

    public LogLevel getLevel() {
        return level;
    }

    @Override
    public String toString() {
        String formattedDate = formatter.format(LocalDateTime.now());
        StringBuilder messageBuilder = new StringBuilder();
        return messageBuilder.append(formattedDate)
                .append(DELIMITER)
                .append(level)
                .append(DELIMITER)
                .append(loggerName)
                .append(DELIMITER)
                .append(": ")
                .append(message)
                .toString();
    }
}
