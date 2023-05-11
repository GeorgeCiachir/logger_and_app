package com.georgeciachir.logger;

import com.georgeciachir.LogLevel;
import com.georgeciachir.config.Appenders;
import com.georgeciachir.defaults.LoggingEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class ApplicationLoggerTest {

    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm";
    private ApplicationLogger underTest = new ApplicationLogger(ApplicationLoggerTest.class.getName());

    @Captor
    private ArgumentCaptor<LoggingEvent> eventCaptor;

    @Test
    public void shouldNotLogWhenRequestedLevelHigherThanTheConfiguredOne() {
        //given
        underTest.setCurrentLevel(LogLevel.INFO);
        Appenders appenders = mock(Appenders.class);
        underTest.setAppenders(appenders);

        //when
        underTest.debug("message");

        //then
        verifyNoInteractions(appenders);
    }

    @Test
    public void shouldLogWhenRequestedLevelLowerThanTheConfiguredOne() {
        //given
        Appenders appenders = mock(Appenders.class);
        underTest.setAppenders(appenders);
        underTest.setCurrentLevel(LogLevel.INFO);
        underTest.setDateTimePattern(DATE_TIME_PATTERN);
        String message = "message";

        //when
        underTest.warn(message);

        //then
        verify(appenders).appendLoggingEvent(eventCaptor.capture());
        LoggingEvent actual = eventCaptor.getValue();
        // too tricky to compare the entire result, as the time is different
        assertTrue(actual.toString().contains(message));
    }

}