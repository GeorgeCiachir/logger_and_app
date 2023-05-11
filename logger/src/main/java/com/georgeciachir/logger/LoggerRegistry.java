package com.georgeciachir.logger;

import com.georgeciachir.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerRegistry {

    private static final Map<String, Logger> EXISTING_LOGGERS = new HashMap<>();

    public static Logger getLogger(String name) {
        return EXISTING_LOGGERS.get(name);
    }

    public static List<Logger> getLoggers() {
        return new ArrayList<>(EXISTING_LOGGERS.values());
    }

    static void addLogger(Logger logger) {
        EXISTING_LOGGERS.put(logger.getName(), logger);
    }
}
