package nl.hu.bep.shopping.dao;

import nl.hu.bep.setup.DatabaseConnectionAzure;
import nl.hu.bep.shopping.model.CategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public static List<CategoryService> getAllCategories() {
        String query = "SELECT id, name, image, description FROM category_project";
        List<CategoryService> categories = new ArrayList<>();

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                CategoryService category = new CategoryService();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setImage(resultSet.getString("image"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public static CategoryService getCategoryById(int categoryId) {
        String query = "SELECT id, name, image, description FROM category_project WHERE id = ?";
        CategoryService category = null;

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = new CategoryService();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    category.setImage(resultSet.getString("image"));
                    category.setDescription(resultSet.getString("description"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return category;
    }
}
