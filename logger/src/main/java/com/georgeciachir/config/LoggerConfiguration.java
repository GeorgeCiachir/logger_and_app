package com.georgeciachir.config;

import java.util.Map;

public class LoggerConfiguration {

    private RootConfig root;
    private Map<String, AppenderConfig> appenderConfigs;

    public void setRoot(RootConfig root) {
        this.root = root;
    }

    public RootConfig getRoot() {
        return root;
    }

    public void setAppenderConfigs(Map<String, AppenderConfig> appenderConfigs) {
        this.appenderConfigs = appenderConfigs;
    }

    public Map<String, AppenderConfig> getAppenderConfigs() {
        return appenderConfigs;
    }
}