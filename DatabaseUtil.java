package com.financetracker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final java.lang.String URL = "jdbc:mysql://localhost:3306/ExpenseManager";
    private static final java.lang.String USER = "root";  // Use MySQL username
    private static final java.lang.String PASSWORD = "@Jaidm2006";  // Use MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
