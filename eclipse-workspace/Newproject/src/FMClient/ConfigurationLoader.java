package FMClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {
    private static Properties properties;

    /**
     * Constructor that loads the configuration properties file.
     * 
     * @param baseDir The base directory where the config file is located.
     */
    public ConfigurationLoader(String baseDir) {
        // Constructing the path dynamically based on baseDir
        String configFilePath = baseDir + File.separator + "src" + File.separator + "FMClient" + File.separator + "config.properties";
        properties = new Properties();
        
        // Attempt to load the config file
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            // Print stack trace and throw a runtime exception with more context
            System.err.println("Failed to load the config file: " + configFilePath);
            e.printStackTrace();
            throw new RuntimeException("Error loading properties file: " + configFilePath, e);
        }
        
        // Check if properties file was loaded successfully (i.e., not empty)
        if (properties.isEmpty()) {
            throw new RuntimeException("Properties file is empty or could not be loaded: " + configFilePath);
        }
        
        // Optionally log the successful loading of the properties file
        System.out.println("Config file loaded successfully: " + configFilePath);
    }

    /**
     * Retrieves a property from the config file.
     * 
     * @param key The key of the property to retrieve.
     * @return The value of the property or null if not found.
     * @throws RuntimeException If the property is not found in the config file.
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println("Property with key '" + key + "' not found in the config file.");
            // Optionally, throw an exception here to indicate missing property
            throw new RuntimeException("Property '" + key + "' not found in the config file.");
        }
        return value;
    }

    /**
     * Retrieves the path to the chromedriver executable.
     * 
     * @param baseDir The base directory for the chromedriver location.
     * @return The path to the chromedriver executable.
     * @throws RuntimeException If the chromedriver file is not found at the expected path.
     */
    public String getDriverPath(String baseDir) {
        String driverPath = baseDir + File.separator + "web-driver" + File.separator + "chromedriver.exe";
        
        // Check if the chromedriver file exists
        File driverFile = new File(driverPath);
        if (!driverFile.exists()) {
            System.err.println("Chromedriver not found at path: " + driverPath);
            throw new RuntimeException("Chromedriver not found at path: " + driverPath);
        }
        return driverPath;
    }
}