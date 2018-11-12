package com.kwantler.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    public static Connection buildConnection(String driverClassName, String url, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, userName, password);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
