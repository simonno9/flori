package nl.hu.bep.shopping.dao;

import nl.hu.bep.shopping.model.FishingReport;
import nl.hu.bep.setup.DatabaseConnectionAzure;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FishingReportDAO {

    // Define a date format for consistent date parsing and formatting
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Method to retrieve all fishing reports
    public static List<FishingReport> selectAllFishingReports() throws ClassNotFoundException, SQLException {
        List<FishingReport> reports = new ArrayList<>();
        String query = "SELECT * FROM fishing_reports";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FishingReport report = new FishingReport(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        dateFormat.format(resultSet.getDate("date")),  // Format date to string
                        resultSet.getString("image")  // Fetch the image as a Base64 string from the database
                );
                reports.add(report);
            }
        }
        return reports;
    }

    // Method to retrieve a specific fishing report by ID
    public static FishingReport selectFishingReportById(int reportId) throws ClassNotFoundException, SQLException {
        FishingReport report = null;
        String query = "SELECT * FROM fishing_reports WHERE id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, reportId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    report = new FishingReport(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            dateFormat.format(resultSet.getDate("date")),  // Format date to string
                            resultSet.getString("image")  // Fetch the image as a Base64 string from the database
                    );
                }
            }
        }
        return report;
    }

    // Method to add a new fishing report
    public static void addFishingReport(FishingReport report) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO fishing_reports (title, description, date, image) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, report.getTitle());
            statement.setString(2, report.getDescription());
            statement.setDate(3, new java.sql.Date(dateFormat.parse(report.getDate()).getTime()));  // Parse and insert date
            statement.setString(4, report.getImage());  // Insert the image as a Base64 string

            statement.executeUpdate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to edit an existing fishing report
    public static boolean updateFishingReport(FishingReport report) throws ClassNotFoundException, SQLException {
        String query = "UPDATE fishing_reports SET title = ?, description = ?, date = ?, image = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, report.getTitle());
            statement.setString(2, report.getDescription());
            statement.setDate(3, new java.sql.Date(dateFormat.parse(report.getDate()).getTime()));  // Parse and update date
            statement.setString(4, report.getImage());  // Update the image as a Base64 string
            statement.setInt(5, report.getId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;  // Return true if the report was updated
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to delete a fishing report
    public static void deleteFishingReport(int id) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM fishing_reports WHERE id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
