package utils;

import org.apache.logging.log4j.Logger;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class TestDataReader {

    private static final Logger log = LoggerUtil.getLogger(TestDataReader.class);
    private static final JsonNode loginData;

    private TestDataReader() {}

    // Loads the JSON file once
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream input = TestDataReader.class
                    .getClassLoader()
                    .getResourceAsStream("testdata/loginData.json");

            if (input == null) {
                throw new RuntimeException("loginData.json not found in resources/testdata/");
            }

            loginData = mapper.readTree(input);
            log.info("loginData.json loaded successfully");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load loginData.json", e);
        }
    }

    private static JsonNode getUserNode(String category, String userType) {
        JsonNode categoryNode = loginData.get(category);

        if (categoryNode == null) {
            throw new IllegalArgumentException(
                    "Category not found in loginData.json: '" + category + "'"
            );
        }

        JsonNode userNode = categoryNode.get(userType);

        if (userNode == null) {
            throw new IllegalArgumentException(
                    "User not found in category '" + category + "': '" + userType + "'"
            );
        }

        return userNode;
    }

    // Get username by user type
    public static String getUsername(String category, String userType) {
        log.info("Getting username for {}/{}", category, userType);
        return getUserNode(category, userType).get("username").asString();
    }

    // Get password by user type
    public static String getPassword(String category, String userType) {
        log.info("Getting password for {}/{}", category, userType);
        return getUserNode(category, userType).get("password").asString();
    }
}