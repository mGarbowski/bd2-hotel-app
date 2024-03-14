package pl.mgarbowski;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public record DatabaseProperties(String url, String username, String password) {
    public static DatabaseProperties loadFromFile(String path) {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseProperties.class.getResourceAsStream(path)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }

        return new DatabaseProperties(
                properties.getProperty("database.url"),
                properties.getProperty("database.username"),
                properties.getProperty("database.password")
        );
    }
}
