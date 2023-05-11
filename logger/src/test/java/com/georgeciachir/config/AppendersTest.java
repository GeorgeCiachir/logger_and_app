package com.georgeciachir.config;

import com.georgeciachir.Appender;
import com.georgeciachir.LogLevel;
import com.georgeciachir.defaults.LoggingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AppendersTest {

    private Appenders underTest;
    private final Appender appender = infoLevelAppender();

    @BeforeEach
    public void setUp() {
        underTest = new Appenders(Set.of(appender));
    }

    @Test
    public void shouldLogWhenRequestedLevelIsLowerThanTheConfiguredOne() {
        //given
        String message = "message";
        LoggingEvent loggingEvent = mock(LoggingEvent.class);
        when(loggingEvent.toString()).thenReturn(message);

        Set.of(LogLevel.INFO, LogLevel.WARNING, LogLevel.ERROR)
                .forEach(level -> {
                    when(loggingEvent.getLevel()).thenReturn(level);

                    //when
                    underTest.appendLoggingEvent(loggingEvent);
                });

        //then
        verify(appender, times(3)).append(message);
    }

    @Test
    public void shouldNotLogWhenRequestedLevelIsHigherThenTheConfiguredOne() {
        //given
        LoggingEvent loggingEvent = mock(LoggingEvent.class);
        when(loggingEvent.getLevel()).thenReturn(LogLevel.DEBUG);

        //when
        underTest.appendLoggingEvent(loggingEvent);

        //then
        verify(appender, never()).append(anyString());
    }

    private Appender infoLevelAppender() {
        Appender appender = mock(Appender.class);
        when(appender.getAcceptedLevels()).thenReturn(Set.of(LogLevel.INFO, LogLevel.WARNING, LogLevel.ERROR));
        return appender;
    }
}