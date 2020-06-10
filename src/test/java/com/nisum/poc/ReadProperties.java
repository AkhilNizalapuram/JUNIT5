package com.nisum.poc;

import java.io.*;
import java.util.Properties;

public class ReadProperties {

    public static String getProperty(String key) {
        Properties prop = new Properties();
        File file = new File("src/test/resources/configurations/config.properties");
        try {
            prop.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.get(key).toString();

    }

}