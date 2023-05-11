package com.georgeciachir;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LogLevelTest {

    @ParameterizedTest
    @EnumSource(LogLevel.class)
    public void eachLogLevelAcceptsOnlyLowerOrSameLevel(LogLevel level) {
        level.getIncludedLevels()
                .forEach(includedLevel -> assertTrue(includedLevel.getIntLevel() <= level.getIntLevel()));
    }

}