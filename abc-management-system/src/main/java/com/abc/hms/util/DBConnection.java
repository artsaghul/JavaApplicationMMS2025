package com.abc.hms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Centralized JDBC connection helper for the ABC Management System.
 * Update DB_URL / DB_USER / DB_PASSWORD for your environment,
 * or set them via environment variables (recommended).
 */
public class DBConnection {

    private static final String DB_URL =
            System.getenv().getOrDefault("ABC_DB_URL",
                    "jdbc:mysql://localhost:3306/abc_management_system?useSSL=false&serverTimezone=UTC");
    private static final String DB_USER =
            System.getenv().getOrDefault("ABC_DB_USER", "root");
    private static final String DB_PASSWORD =
            System.getenv().getOrDefault("ABC_DB_PASSWORD", "");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found on classpath.", e);
        }
    }

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
