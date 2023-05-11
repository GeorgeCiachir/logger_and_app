package com.georgeciachir;


import com.georgeciachir.config.Appenders;

public interface Logger {

    void debug(String message);

    void info(String message);

    void warn(String message);

    void error(String message);

    String getName();

    void setCurrentLevel(final LogLevel currentLevel);

    void setDateTimePattern(String dateTimePattern);

    void setAppenders(Appenders appenders);
}
