package config;

import utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Logger log = LoggerUtil.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    // Static block runs ONCE when the class is first loaded
    // Loads config.properties before any test starts
    static {
        try {
            // Reads from src/main/resources/config.properties
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new FileNotFoundException(
                        "config.properties not found in src/main/resources"
                );
            }

            properties.load(input);
            log.info("config.properties loaded successfully");

        } catch (IOException e) {
            log.error("Failed to load config.properties: {}", e.getMessage());
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    // Private constructor — never instantiate this class
    private ConfigReader() {}

    // Core method — gets a value by key
    public static String get(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            log.error("Key '{}' not found in config.properties", key);
            throw new RuntimeException(
                    "Missing key in config.properties: '" + key + "'"
            );
        }

        return value.trim();  // .trim() removes accidental spaces around values
    }
}
