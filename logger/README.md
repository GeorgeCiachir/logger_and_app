## Possible improvements

- logger configuration validation in the ConfigurationManager class
- possibility to set logger level per package and per class, not only a root/global level
- need to implement the lookup mechanism in the AppenderFactory to retrieve client defined appenders
- maybe find a better solution to reduce startup time -> for now I implemented a variant of the double double-checked locking singleton with synchronization on the requested class, in order to allow m-threading for different classes
- implement something similar an Observer pattern in the LoggerConfigurer.updateLoggers so that loggers implement their own mechanism to perform atomic updating   