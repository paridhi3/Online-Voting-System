package com.company.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	 private static final String URL = System.getenv("MySQL_URL");
	 private static final String USERNAME = System.getenv("MySQL_username");
	 private static final String PASSWORD = System.getenv("MySQL_pw");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
    	System.out.println("Building Connection");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}