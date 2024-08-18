package nl.hu.bep.shopping.dao;

import nl.hu.bep.setup.DatabaseConnectionAzure;
import nl.hu.bep.shopping.model.Charter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharterDAO {

    public static List<Charter> selectAllCharters() throws ClassNotFoundException, SQLException {
        List<Charter> charters = new ArrayList<>();
        String query = "SELECT charter_id, title, description, price, availability_status, image, deposit, people FROM charters";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Charter charter = new Charter(
                        resultSet.getInt("charter_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getString("availability_status"),
                        resultSet.getString("image"),
                        resultSet.getInt("deposit"), // Added deposit
                        resultSet.getString("people") // Added people
                );
                charters.add(charter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving charters", e);
        }

        return charters;
    }

    public static void addCharter(Charter charter) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO charters (title, description, price, availability_status, image, deposit, people) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, charter.getTitle());
            statement.setString(2, charter.getDescription());
            statement.setInt(3, charter.getPrice());
            statement.setString(4, charter.getavailability_status());
            statement.setString(5, charter.getImage());
            statement.setInt(6, charter.getDeposit()); // Added deposit
            statement.setString(7, charter.getPeople()); // Added people

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding charter", e);
        }
    }

    public static void editCharter(Charter charter) throws ClassNotFoundException, SQLException {
        String query = "UPDATE charters SET title = ?, description = ?, price = ?, availability_status = ?, image = ?, deposit = ?, people = ? WHERE charter_id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, charter.getTitle());
            statement.setString(2, charter.getDescription());
            statement.setInt(3, charter.getPrice());
            statement.setString(4, charter.getavailability_status());
            statement.setString(5, charter.getImage());
            statement.setInt(6, charter.getDeposit()); // Added deposit
            statement.setString(7, charter.getPeople()); // Added people
            statement.setInt(8, charter.getCharter_Id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating charter", e);
        }
    }

    public static void deleteCharter(int charterId) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM charters WHERE charter_id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, charterId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting charter", e);
        }
    }

    public static Charter selectCharterById(int charterId) throws ClassNotFoundException, SQLException {
        Charter charter = null;
        String query = "SELECT charter_id, title, description, price, availability_status, image, deposit, people FROM charters WHERE charter_id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, charterId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    charter = new Charter(
                            resultSet.getInt("charter_id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getInt("price"),
                            resultSet.getString("availability_status"),
                            resultSet.getString("image"),
                            resultSet.getInt("deposit"), // Added deposit
                            resultSet.getString("people") // Added people
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving charter by ID", e);
        }

        return charter;
    }
}
