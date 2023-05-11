package com.georgeciachir.config;

import com.georgeciachir.Appender;
import com.georgeciachir.AppenderBuilder;
import com.georgeciachir.defaults.ConsoleAppender;
import com.georgeciachir.defaults.EmailAppender;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AppenderFactory {

    private static final Appender DEFAULT_CONSOLE_APPENDER = new ConsoleAppender();
    private static final Map<String, AppenderBuilder> DEFAULT_APPENDERS = buildDefaultAppenders();

    Appenders create(LoggerConfiguration loggerConfiguration) {
        Map<String, AppenderConfig> appenderConfigs = loggerConfiguration.getAppenderConfigs();
        RootConfig rootConfig = loggerConfiguration.getRoot();

        Set<Appender> appenders = appenderConfigs.entrySet().stream()
                .map(entry -> createAppender(entry, rootConfig))
                .filter(Objects::nonNull)
                .collect(toSet());

        if (appenders.isEmpty()) {
            appenders.add(DEFAULT_CONSOLE_APPENDER);
        }

        return new Appenders(appenders);
    }

    private static Appender createAppender(Map.Entry<String, AppenderConfig> e, RootConfig inheritedDefaults) {
        AppenderBuilder builder = DEFAULT_APPENDERS.get(e.getKey());
        Appender appender = null;
        if (builder != null) {
            appender = builder.build(e.getValue(), inheritedDefaults);
        } else {
            AppenderBuilder otherBuilder = findAdditionallyDefinedBuilders(e);
            if (otherBuilder != null) {
                appender = otherBuilder.build(e.getValue(), inheritedDefaults);
            }
            DEFAULT_CONSOLE_APPENDER.append("Consider defining an appender named [" + e.getKey() + "]");
        }

        if (appender != null && (appender.getLevel().getIntLevel() > inheritedDefaults.getLevel().getIntLevel())) {
            DEFAULT_CONSOLE_APPENDER.append("Consider lowering the logging level for appender [" + appender.getName() + "] to match the root configuration");
        }

        return appender;
    }

    private static AppenderBuilder findAdditionallyDefinedBuilders(Map.Entry<String, AppenderConfig> e) {
        // look for user defined appender
        // to be implemented
        return null;
    }

    private static Map<String, AppenderBuilder> buildDefaultAppenders() {
        Map<String, AppenderBuilder> defaults = new HashMap<>();
        defaults.put(ConsoleAppender.NAME, ConsoleAppender.builder());
        defaults.put(EmailAppender.NAME, EmailAppender.builder());
        return defaults;
    }
}
