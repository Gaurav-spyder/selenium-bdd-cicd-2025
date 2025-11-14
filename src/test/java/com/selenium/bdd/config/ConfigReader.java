package com.selenium.bdd.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    // Static initialization block runs only once when the class is loaded
    static {
        try {
        	FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file!");
        }
    }

    // Method to fetch value by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
