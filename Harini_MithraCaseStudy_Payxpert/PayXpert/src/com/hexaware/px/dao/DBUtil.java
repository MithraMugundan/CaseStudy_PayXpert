package com.hexaware.px.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil { // Factory class

    // Factory method to get a database connection
    public static Connection getDBConnection() throws SQLException {
        FileReader fr = null;
        Properties prop = null;
        
        try {
            // Read the properties file that contains connection information
            fr = new FileReader("src/Database.properties");
            prop = new Properties();
            prop.load(fr);  // Load the properties into the prop object
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception
            throw new SQLException("Error reading the database properties file", e);
        }

        // Retrieve the properties from the file
        String url = prop.getProperty("url");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        try {
            // Register MySQL JDBC driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exception
            throw new SQLException("Error registering MySQL JDBC driver", e);
        }

        // Return the connection object using the properties
        return DriverManager.getConnection(url, username, password);
    }
}
