package nl.hu.bep.setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionAzure {
    private static final String URL = "jdbc:postgresql://bradinteriordesign.postgres.database.azure.com:5432/floridacharter?sslmode=require";
    private static final String USER = "simon";
    private static final String PASSWORD = "Sw2s0s0s3Sw20!";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connection to Azure PostgreSQL database was successful!");
        return connection;
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to Azure PostgreSQL database.");
        }
    }
}
