package nl.hu.bep.shopping.dao;

import nl.hu.bep.setup.DatabaseConnectionAzure;
import nl.hu.bep.shopping.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public static User getUser(String username) {
        String query = "SELECT username, password, role, image FROM users WHERE username = ?";
        logger.info("Executing query: " + query);
        logger.info("With username: " + username);

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("image")

                    );
                    logger.info("User found: " + user.getUsername() + " with role: " + user.getRole());
                    return user;
                } else {
                    logger.warning("No user found with username: " + username);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.severe("Database connection or query failed: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
