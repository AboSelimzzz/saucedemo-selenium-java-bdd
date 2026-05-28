package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    // Private constructor — this class should never be instantiated
    private LoggerUtil() {}

    // Each class gets its OWN logger showing its own name in logs
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
