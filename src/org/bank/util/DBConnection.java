package org.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/bank_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "A+b33B-a"; // change if needed

    public static Connection getConnection() {
        Connection connection = null;

        try {
            // Load Driver (optional in new versions, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
