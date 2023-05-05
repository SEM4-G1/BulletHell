package dk.sdu.group.one.services;

public interface LoggingService {

    void log(Class<?> clazz, String message);

    void error(Class<?> clazz,String message);

    void warn(Class<?> clazz,String message);

    void info(Class<?> clazz,String message);

    void debug(Class<?> clazz,String message);

    void trace(Class<?> clazz,String message);

    void addLoggingClass(Class<?> clazz);
}
