package dk.sdu.group.one.logging;

import dk.sdu.group.one.services.LoggingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class LoggingImplementation implements LoggingService {
    private final Map<Class<?>, Logger> loggers = new HashMap<>();


    @Override
    public void log(Class<?> clazz, String message) {
        getLogger(clazz);
        loggers.get(clazz).info(message);
    }

    @Override
    public void error(Class<?> clazz, String message) {
        getLogger(clazz);
        loggers.get(clazz).error(message);
    }

    @Override
    public void warn(Class<?> clazz, String message) {
        getLogger(clazz);
        loggers.get(clazz).warn(message);
    }

    @Override
    public void info(Class<?> clazz, String message) {
        getLogger(clazz);
        loggers.get(clazz).info(message);
    }

    @Override
    public void debug(Class<?> clazz, String message) {
        getLogger(clazz);
        loggers.get(clazz).debug(message);
    }

    @Override
    public void trace(Class<?> clazz, String message) {
        getLogger(clazz);
        loggers.get(clazz).trace(message);
    }

    @Override
    public void addLoggingClass(Class<?> clazz) {
        loggers.put(clazz, LogManager.getLogger(clazz));
    }

    private Logger getLogger(Class<?> clazz) {
        if (!loggers.containsKey(clazz)) addLoggingClass(clazz);
        return loggers.get(clazz);
    }
}
