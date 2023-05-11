package com.georgeciachir;

import com.georgeciachir.config.AppenderConfig;
import com.georgeciachir.config.RootConfig;

public interface AppenderBuilder {

    Appender build(AppenderConfig config, RootConfig inherited);
}
