package nl.hu.bep.shopping.dao;

import nl.hu.bep.shopping.model.CharterBooking;
import nl.hu.bep.setup.DatabaseConnectionAzure;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CharterBookingDAO {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Method to retrieve all charter bookings
    public static List<CharterBooking> selectAllCharterBookings() throws ClassNotFoundException, SQLException {
        List<CharterBooking> bookings = new ArrayList<>();
        String query = "SELECT cb.*, c.price, c.description, c.title FROM charter_bookings cb JOIN charters c ON cb.charter_id = c.charter_id";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                CharterBooking booking = new CharterBooking(
                        resultSet.getInt("bookingId"),
                        resultSet.getString("customerName"),
                        resultSet.getString("customerEmail"),
                        resultSet.getInt("charter_id"),
                        dateFormat.format(resultSet.getDate("bookingDate")),
                        resultSet.getString("specialRequests"),
                        resultSet.getString("image"),  // Fetch the image from the database
                        resultSet.getInt("price"),  // Fetch the price from the database
                        resultSet.getString("description"),  // Fetch the description from the database
                        resultSet.getString("title")  // Fetch the title from the database
                );
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // Method to retrieve a specific charter booking by ID
    public static CharterBooking selectCharterBookingById(int bookingId) throws ClassNotFoundException, SQLException {
        CharterBooking booking = null;
        String query = "SELECT cb.*, c.price, c.description, c.title FROM charter_bookings cb JOIN charters c ON cb.charter_id = c.charter_id WHERE cb.bookingId = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bookingId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    booking = new CharterBooking(
                            resultSet.getInt("bookingId"),
                            resultSet.getString("customerName"),
                            resultSet.getString("customerEmail"),
                            resultSet.getInt("charter_id"),
                            dateFormat.format(resultSet.getDate("bookingDate")),
                            resultSet.getString("specialRequests"),
                            resultSet.getString("image"),  // Fetch the image from the database
                            resultSet.getInt("price"),  // Fetch the price from the database
                            resultSet.getString("description"),  // Fetch the description from the database
                            resultSet.getString("title")  // Fetch the title from the database
                    );
                }
            }
        }
        return booking;
    }

    // Method to add a new charter booking
    public static void addCharterBooking(CharterBooking booking) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO charter_bookings (customerName, customerEmail, charter_id, bookingDate, specialRequests, image) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, booking.getCustomerName());
            statement.setString(2, booking.getCustomerEmail());
            statement.setInt(3, booking.getCharterId());
            statement.setDate(4, new java.sql.Date(dateFormat.parse(booking.getBookingDate()).getTime()));  // Parsing String back to Date
            statement.setString(5, booking.getSpecialRequests());
            statement.setString(6, booking.getImage());  // Insert the image as a Base64 string

            statement.executeUpdate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to edit an existing charter booking
    public static boolean updateCharterBooking(CharterBooking booking) throws ClassNotFoundException, SQLException {
        String query = "UPDATE charter_bookings SET customerName = ?, customerEmail = ?, charter_id = ?, bookingDate = ?, specialRequests = ?, image = ? WHERE bookingId = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, booking.getCustomerName());
            statement.setString(2, booking.getCustomerEmail());
            statement.setInt(3, booking.getCharterId());
            statement.setDate(4, new java.sql.Date(dateFormat.parse(booking.getBookingDate()).getTime()));  // Parsing String back to Date
            statement.setString(5, booking.getSpecialRequests());
            statement.setString(6, booking.getImage());  // Update the image
            statement.setInt(7, booking.getBookingId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;  // Return true if the booking was updated
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to delete a charter booking
    public static void deleteCharterBooking(int id) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM charter_bookings WHERE bookingId = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
