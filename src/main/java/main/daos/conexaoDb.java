package main.daos;

import java.sql.*;

public class conexaoDb {
    private static final String URL ="jdbc:mysql://localhost:3306/farmhub";
    private static final String USER = "root";
    private static final String PASSWORD = "Lbn26827503";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
