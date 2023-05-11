package com.georgeciachir.controller;

import com.georgeciachir.config.ConfigurationManager;
import com.georgeciachir.config.LoggerConfiguration;
import com.georgeciachir.logger.LoggerFactory;
import com.georgeciachir.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerController.class);

    @PostMapping("/reconfigure")
    public void reconfigureLoggers(@RequestBody LoggerConfiguration loggerConfiguration) {
        ConfigurationManager.updateConfiguration(loggerConfiguration);
    }

    @PostMapping("/log")
    public void log(@RequestBody String message, @RequestParam String level) {
        if ("debug".equals(level)) {
            LOGGER.debug(message);
        }

        if ("info".equals(level)) {
            LOGGER.info(message);
        }

        if ("warning".equals(level)) {
            LOGGER.warn(message);
        }

        if ("error".equals(level)) {
            LOGGER.error(message);
        }
    }

    @PostMapping("/log/all")
    public void logAll(@RequestBody String message) {
        LOGGER.debug(message);
        LOGGER.info(message);
        LOGGER.warn(message);
        LOGGER.error(message);
    }
}
