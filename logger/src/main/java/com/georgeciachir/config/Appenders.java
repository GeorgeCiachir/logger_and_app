package com.georgeciachir.config;

import com.georgeciachir.Appender;
import com.georgeciachir.LogLevel;
import com.georgeciachir.defaults.LoggingEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Appenders {

    private final Map<LogLevel, List<Appender>> appendersForLoggingLevels;

    Appenders(Set<Appender> appenders) {
        if (appenders == null || appenders.isEmpty()) {
            throw new IllegalArgumentException("At least one appender must be defined");
        }

        // each appender will be present in the values of each accepted log level
        this.appendersForLoggingLevels = appenders.stream()
                .map(this::getAcceptedLevelsForAppender)
                .flatMap(map -> map.entrySet().stream())
                .collect(groupingBy(Map.Entry::getKey, TreeMap::new, Collectors.mapping(Map.Entry::getValue, toList())));
    }

    public void appendLoggingEvent(LoggingEvent event) {
        appendersForLoggingLevels.getOrDefault(event.getLevel(), emptyList())
                .forEach(appender -> appender.append(event.toString()));
    }

    private Map<LogLevel, Appender> getAcceptedLevelsForAppender(Appender appender) {
        return appender.getAcceptedLevels().stream()
                .collect(toMap(identity(), level -> appender));
    }
}
