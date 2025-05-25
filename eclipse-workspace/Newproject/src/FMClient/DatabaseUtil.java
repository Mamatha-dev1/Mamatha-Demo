package FMClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static Connection connection = null;

    static {
        // Load database configuration using ConfigurationLoader
        ConfigurationLoader configLoader = new ConfigurationLoader(System.getProperty("user.dir"));
        
        // Get database details from the config properties
        String dbUrl = configLoader.getProperty("db.url");
        String dbUsername = configLoader.getProperty("db.username");
        String dbPassword = configLoader.getProperty("db.password");
        
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get the connection
    public static Connection getConnection() {
        return connection;
    }

    // Method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
