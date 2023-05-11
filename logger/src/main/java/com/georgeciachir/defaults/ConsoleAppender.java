package com.georgeciachir.defaults;

import com.georgeciachir.LogLevel;
import com.georgeciachir.config.AppenderConfig;
import com.georgeciachir.config.RootConfig;
import com.georgeciachir.Appender;
import com.georgeciachir.AppenderBuilder;

public class ConsoleAppender implements Appender {

    public static final String NAME = "console";
    private static final AppenderBuilder BUILDER = new ConsoleAppenderBuilder();
    private LogLevel level;

    @Override
    public void append(final String formattedMessage) {
        System.out.println(ConsoleAppender.class.getSimpleName() + "=>" + formattedMessage);
    }

    @Override
    public LogLevel getLevel() {
        return level;
    }

    public String getName() {
        return NAME;
    }

    public static AppenderBuilder builder() {
        return BUILDER;
    }

    private static class ConsoleAppenderBuilder implements AppenderBuilder {

        @Override
        public Appender build(AppenderConfig config, RootConfig inherited) {
            ConsoleAppender consoleAppender = new ConsoleAppender();
            consoleAppender.level = config.getLevel() != null
                    ? config.getLevel()
                    : inherited.getLevel();

            consoleAppender.level = config.getLevel();
            return consoleAppender;
        }
    }
}
