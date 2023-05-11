package com.georgeciachir.defaults;

import com.georgeciachir.Appender;
import com.georgeciachir.AppenderBuilder;
import com.georgeciachir.LogLevel;
import com.georgeciachir.config.AppenderConfig;
import com.georgeciachir.config.RootConfig;

public class EmailAppender implements Appender {

    public static final String NAME = "email";
    private static final AppenderBuilder BUILDER = new EmailAppenderBuilder();
    private LogLevel level;

    @Override
    public void append(final String formattedMessage) {
        System.out.println(EmailAppenderBuilder.class.getSimpleName() + "=>" + formattedMessage);
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

    private static class EmailAppenderBuilder implements AppenderBuilder {

        @Override
        public Appender build(AppenderConfig config, RootConfig inherited) {
            EmailAppender emailAppender = new EmailAppender();
            emailAppender.level = config.getLevel() != null
                    ? config.getLevel()
                    : inherited.getLevel();

            emailAppender.level = config.getLevel();
            return emailAppender;
        }
    }
}
