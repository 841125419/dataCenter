package com.kwantler.util.common;

import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigProperties {

    private static Properties p;

    static{
        p = new Properties();
        try {
            p.load(new InputStreamReader(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"),
                    "UTF-8")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String propertiesName){
        return p.getProperty(propertiesName);
    }

}
