
package nl.hu.bep.shopping.dao;

import nl.hu.bep.setup.DatabaseConnection;
import nl.hu.bep.setup.DatabaseConnectionAzure;
import nl.hu.bep.shopping.model.Product;
import nl.hu.bep.shopping.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static  List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("product_name"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("status"),
                        resultSet.getString("colors"),
                        resultSet.getString("sizes"),
                        resultSet.getString("images")

                );
                products.add(product);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error executing query: " + query);
            System.err.println("SQLException: " + e.getMessage());
            throw new RuntimeException("Error retrieving reviews", e);
        }

        if (products.isEmpty()) {
            System.out.println("No reviews found in the database.");
        } else {
            System.out.println("Reviews retrieved successfully.");
        }

        return products;
    }
    public static void editProduct(Product product) {
        String query = "UPDATE products SET product_name = ?, description = ?, quantity = ?, status = ?, colors = ?, sizes = ?, images = ?, price = ?  WHERE id = ?";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getProduct_name());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getStatus());
            statement.setString(5, product.getColors());
            statement.setString(6, product.getSizes());
            statement.setString(7, product.getImage());
            statement.setInt(8, product.getPrice());
            statement.setInt(9, product.getId());


            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error executing query: " + query);
            System.err.println("SQLException: " + e.getMessage());
            throw new RuntimeException("Error updating product", e);
        }
    }
    public static void addProduct(Product product) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO products (product_name, description, quantity, status, colors, sizes,images,price) VALUES (?, ?, ?, ?, ?, ?,?,?)";

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProduct_name());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getStatus());
            statement.setString(5, product.getColors());

            statement.setString(6, product.getSizes());
            statement.setString(7, product.getImage());

            statement.executeUpdate();
        }
    }
    public static Product selectProductById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        Product product = null;

        try (Connection connection = DatabaseConnectionAzure.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getInt("price"),
                            resultSet.getString("product_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("quantity"),
                            resultSet.getString("status"),
                            resultSet.getString("colors"),
                            resultSet.getString("sizes"),
                            resultSet.getString("images")
                    );
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error executing query: " + query);
            System.err.println("SQLException: " + e.getMessage());
            throw new RuntimeException("Error retrieving product", e);
        }

        return product;
    }
}
