package Mamatha;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configLoader {
    private static  Properties properties;

    // Load the config path
    public configLoader(String baseDir) {
        // Using the full path you provided for config.properties
        String configFilePath = "C:\\Users\\mamatha.kp\\eclipse-workspace\\Newproject\\src\\Mamatha\\config.properties";
        
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get any property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    
    public String getDriverPath(String baseDir) {
        
        return null; 
    }
    public  String getExportFormat() {
        return properties.getProperty("export.format", "CSV"); // Default to CSV if not set
    }
}