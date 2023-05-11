package com.georgeciachir;

import java.util.Set;

public interface Appender {

    void append(String formattedMessage);

    LogLevel getLevel();

    String getName();

    default Set<LogLevel> getAcceptedLevels() {
        return getLevel().getIncludedLevels();
    }
}
