package com.georgeciachir.logger;

import com.georgeciachir.Logger;
import com.georgeciachir.config.ConfigurationManager;

public class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        String loggerName = clazz.getName();
        Logger logger = LoggerRegistry.getLogger(loggerName);

        if (logger != null) {
            return logger;
        }

        synchronized (clazz) {
            logger = LoggerRegistry.getLogger(loggerName);
            if (logger != null) {
                return logger;
            }
            logger = new ApplicationLogger(loggerName);
            LoggerRegistry.addLogger(logger);
        }

        ConfigurationManager.configure(logger);

        return logger;
    }
}
