package com.georgeciachir;

import java.util.Map;
import java.util.Set;

public enum LogLevel {

    ERROR(0),
    WARNING(1),
    INFO(2),
    DEBUG(3);

    private final int intLevel;
    private static final Map<LogLevel, Set<LogLevel>> INCLUDED_LEVELS = Map.ofEntries(
            Map.entry(ERROR, Set.of(ERROR)),
            Map.entry(WARNING, Set.of(ERROR, WARNING)),
            Map.entry(INFO, Set.of(ERROR, WARNING, INFO)),
            Map.entry(DEBUG, Set.of(ERROR, WARNING, INFO, DEBUG))
    );

    LogLevel(int intLevel) {
        this.intLevel = intLevel;
    }

    public int getIntLevel() {
        return intLevel;
    }

    public Set<LogLevel> getIncludedLevels() {
        return INCLUDED_LEVELS.get(this);
    }
}
